bodyParser = require("body-parser"),
  swaggerJsdoc = require("swagger-jsdoc"),
  swaggerUi = require("swagger-ui-express");
const path = require("path");

const options = {
  swaggerDefinition: {
    openapi: '3.0.0',
    info: {
      title: 'Fleet Service API',
      version: '1.0.0',
      description: 'API documentation for the Fleet Service',
    },
    servers: [
      {
        url: `http://localhost:${process.env.PORT || 5000}`,
      },
    ],
    components: {
      securitySchemes: null
    }
  },
  apis: ['./routes/*.js'], // Path to your API docs
};


const swaggerSpec = swaggerJsdoc(options);

// console.log("Swagger generated spec:", JSON.stringify(swaggerSpec, null, 2));

module.exports.swaggerSpec = swaggerSpec;