// ============ Modules
const express = require("express");
const swaggerUi = require('swagger-ui-express');
const { connect_db } = require("./configs/Database");

// ============ Import Middlewares
const LoginRequired = require("./middlewares/Auth");
const loggerMiddleware = require("./middlewares/Logger");
const globalErrorHandler = require("./middlewares/ErrorHandler");
const Response = require("./middlewares/Response");
const { registerWithEureka, sendHeartbeat } = require("./middlewares/Eureka");
const swaggerSpec = require("./configs/Swagger");

// ============ Import Routes
const vehicleRouter = require("./routes/Vehicle.route");
const tripRouter = require("./routes/Trip.route");
const driverRouter = require("./routes/Driver.route");

const PORT = process.env.PORT;

require("dotenv").config({ path: ".env" });

const app = express();


// ============ Middleware Use 
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(loggerMiddleware);
app.use(Response);
app.use("/api-docs", swaggerUi.serve, swaggerUi.setup(swaggerSpec.swaggerSpec));

app.get("/", (req, res, next) => {
  res.json({
    succes: true,
  });
});


// My routers
app.use("/vehicle", vehicleRouter);
app.use("/trip", tripRouter);
app.use("/driver", driverRouter);



app.all("/", (req, res, next) => {
  res.status(404).Response({ message: "Url non trouvée" });
});


app.use(globalErrorHandler);


app.listen(PORT, "0.0.0.0", async () => {
  console.log(`App running on http://localhost:${PORT}`);

  setTimeout(async () => {
    await connect_db();
    registerWithEureka();
  }, 3000);
});


// Envoi de heartbeat à Eureka toutes les 30 secondes
setInterval(() => {
  sendHeartbeat();
}, 30000);

