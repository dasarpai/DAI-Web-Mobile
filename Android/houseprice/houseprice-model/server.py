from flask import Flask, request, jsonify
import pickle
import numpy as np

# Load the model
with open('model.pkl', 'rb') as f:
    model = pickle.load(f)

app = Flask(__name__)

@app.route('/')
def home():
    return """<h1>Welcome to the House Price Prediction API</h1><p></br>
    Use the /predict endpoint to predict house prices.</br></br>
    The input should be like </br> 
    [3, 47.5112, -122.257, 1180, False, 1955], </br>
    [4, 47.7210, -122.319, 2570, False, 1991], </br>
    [8, 47.7210, -122.319, 2570, True, 2000] </br></br>

    These 6 parameters are </br>
    House Grade (integer value),</br>
    Latitute of House (float value),</br>
    Latitute of House (float value),</br>
    Living Area in Sqft.,</br>
    House if waterfront True/False,</br>
    House Built Year,</br></br>

    As service is running you can also type on command prompt. </br>
    #curl -X POST http://192.168.29.246:5000/predict -H "Content-Type: application/json" -d "{\"input_data\": [[3, 47.5112, -122.257, 1180, false, 1955]]}"
    
    </br></br> OR with named Parameters. </br>
    curl -G   -d "grade=3"   -d "lat=47.5112"   -d "long=-122.257"   -d "sqft=1180"   -d "waterfront=false"   -d "yr_built=1955"   http://192.168.29.246:5000/predict

    </br>
     </br>
    -G indicates that this is a GET request. </br>
    Each -d option specifies a query parameter that will be included in the request.</br>
    The base URL is http://192.168.29.246:5000/predict.</br>

    

    </br></br>
    on browser url you can type following </br>
    http://192.168.29.246:5000/predict?grade=3&lat=47.5112&long=-122.257&sqft=1180&waterfront=false&yr_built=1955
    
    </p>"""


@app.route('/predict', methods=['GET', 'POST'])
def predict():
    if request.method == 'POST':
        # Ensure the request has 'application/json' Content-Type
        if request.content_type == 'application/json':
            input_data = request.json['input_data']
        else:
            return jsonify({'error': 'Unsupported Media Type'}), 415
    else:  # GET request
        # Extract data from query parameters for GET request
        try:
            grade = int(request.args.get('grade'))
            lat = float(request.args.get('lat'))
            long = float(request.args.get('long'))
            sqft = int(request.args.get('sqft'))
            waterfront = request.args.get('waterfront') == 'true'
            yr_built = int(request.args.get('yr_built'))
        
            input_data = [[grade, lat, long, sqft, waterfront, yr_built]]
        except (TypeError, ValueError) as e:
            return jsonify({'error': f'Invalid input data: {e}'}), 400
        

    # input_data = request.json['input_data']

    # Convert input data to a NumPy array with dtype=object to handle mixed types
    input_array = np.array(input_data, dtype=object)

    # Predict the house price
    predictions = np.round(model.predict(input_array),0)

    # print log in server window
    print(input_array,"predicted price: ", predictions)

    # Return the predictions as a JSON response
    return jsonify({'predictions': predictions.tolist()})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)


