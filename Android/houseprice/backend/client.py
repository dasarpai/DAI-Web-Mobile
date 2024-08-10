import requests

# Define the API endpoint
url = 'http://127.0.0.1:5000/predict'

# Sample input data
input_data = [
    [3, 47.5112, -122.257, 1180, False, 1955],
    [4, 47.7210, -122.319, 2570, False, 1991],
    [8, 47.7210, -122.319, 2570, False, 200]
]

# Prepare the payload
payload = {
    'input_data': input_data
}

# Send the request
response = requests.post(url, json=payload)

# Print the response
if response.status_code == 200:
    predictions = response.json().get('predictions', [])
    print('Predictions:', predictions)
else:
    print('Failed to get predictions:', response.text)
