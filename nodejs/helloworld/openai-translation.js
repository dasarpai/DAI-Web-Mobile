const OpenAI = require("openai");

import OpenAI from "openai";


// Install the 'openai' package using: npm install openai

const openai = new OpenAI({
  apiKey: process.env.OPENAI_API_KEY,
});



// Function to translate text using OpenAI
async function translateText(inputText, targetLanguage) {
  try {
    const response = await openai.translation.translate({
      text: inputText,
      targetLanguage,
    });

    // Extract the translated text from the response
    const translatedText = response.data.translations[0].translatedText;
    return translatedText;
  } catch (error) {
    console.error("Error translating text:", error.message);
    return null;
  }
}

// Example usage
const inputText = "Hello, world!"; // Replace with your own text
const targetLanguage = "fr"; // Replace with the desired target language code (e.g., "fr" for French)

translateText(inputText, targetLanguage)
  .then((translatedText) => {
    if (translatedText) {
      console.log(`Translated text: ${translatedText}`);
    } else {
      console.log("Translation failed.");
    }
  })
  .catch((err) => {
    console.error("Translation error:", err.message);
  });
