from flask import Flask, request, jsonify
import pickle
import numpy as np

# Load the model
with open('model.pkl', 'rb') as f:
    model = pickle.load(f)

app = Flask(__name__)

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json
    input_data = data['input_data']
    
    # Convert input data to a NumPy array with dtype=object to handle mixed types
    input_array = np.array(input_data, dtype=object)
    
    # Predict the house price
    predictions = np.round(model.predict(input_array),0)
    
    # Return the predictions as a JSON response
    return jsonify({'predictions': predictions.tolist()})

if __name__ == '__main__':
    app.run(debug=True)
