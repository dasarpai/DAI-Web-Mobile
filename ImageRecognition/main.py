from fastapi import FastAPI, File, UploadFile
import requests
import io

app = FastAPI()

AZURE_VISION_ENDPOINT = "https://dasarpai-image-detection.cognitiveservices.azure.com"
AZURE_VISION_SUBSCRIPTION_KEY = "7ca81597171742dfa7d3afd43aa7928d"
AZURE_VISION_ANALYZE_URL = f"{AZURE_VISION_ENDPOINT}/vision/v3.2/analyze"

@app.post("/analyze-image/")
async def analyze_image(file: UploadFile = File(...)):
    print(AZURE_VISION_ANALYZE_URL)
    print ("Hi Hari")
    image_data = await file.read()
    
    headers = {
        'Ocp-Apim-Subscription-Key': AZURE_VISION_SUBSCRIPTION_KEY,
        'Content-Type': 'application/octet-stream'
    }
    params = {
        'visualFeatures': 'Categories,Description,Color'
    }
    
    response = requests.post(AZURE_VISION_ANALYZE_URL, headers=headers, params=params, data=image_data)

    if response.status_code == 200:
        print("Success!")
        print(response.json())
    else:
        print(f"Error: {response.status_code}")
        print(response.text)

    response.raise_for_status()
    analysis = response.json()
    return analysis

if __name__ == "__main__":
    print ("Hi Hari")
    import uvicorn
    uvicorn.run(app, host="127.0.0.1/analyze-image", port=8000)



# from fastapi import FastAPI, HTTPException, Request
# from pydantic import BaseModel
# import requests

# app = FastAPI()

# class ImageUrl(BaseModel):
#     url: str

# AZURE_VISION_ENDPOINT = "https://dasarpai-image-detection.cognitiveservices.azure.com"
# AZURE_VISION_SUBSCRIPTION_KEY = "4bedfc880bcb4057b16ac2fb6cc38a63"
# AZURE_VISION_ANALYZE_URL = f"{AZURE_VISION_ENDPOINT}/vision/v3.2/analyze"

# @app.post("/analyze-image/")
# async def analyze_image(image_url: ImageUrl):
#     headers = {
#         'Ocp-Apim-Subscription-Key': AZURE_VISION_SUBSCRIPTION_KEY,
#         'Content-Type': 'application/json'
#     }
#     data = {
#         "url": image_url.url
#     }
#     response = requests.post(AZURE_VISION_ANALYZE_URL, headers=headers, json=data)

#     if response.status_code == 200:
#         return response.json()
#     else:
#         raise HTTPException(status_code=response.status_code, detail=response.text)


# import requests

# url = "http://127.0.0.1:8000/analyze-image/"
# image_url = {
#     "url": "https://images.freeimages.com/images/large-previews/502/cat-1393633.jpg"
# }

# print( AZURE_VISION_SUBSCRIPTION_KEY + '\n' + AZURE_VISION_ENDPOINT + '\n' +AZURE_VISION_ANALYZE_URL)

# response = requests.post(url, json=image_url)


# if response.status_code == 200:
#     print("Success!")
#     print(response.json())
# else:
#     print(f"Error: {response.status_code}")
#     print(response.text)
