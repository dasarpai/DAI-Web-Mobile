import * as functions from 'firebase-functions';

export const predictHousePrice = functions.https.onRequest((request, response) => {
  // Your code to predict house price goes here

  // Example response
  const price = 100000;
  response.json({ price: price });
});
