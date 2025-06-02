const express = require("express");

const vehicleRouter = express.Router();


const {
    getAllVehicle, createVehicle, updateVehicle, deleteVehicle, getVehicleById
  } = require("../controllers/Vehicle.controller");

  
vehicleRouter.get("/", getAllVehicle);

vehicleRouter.get("/:id", getVehicleById);

vehicleRouter.delete("/:id", deleteVehicle);

vehicleRouter.post("/", createVehicle);

vehicleRouter.patch("/:id", updateVehicle);
  
module.exports = vehicleRouter;
