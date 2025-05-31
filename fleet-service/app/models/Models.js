const { Vehicle, CreateVehicleModel, UpdateVehicleModel, } = require("./Vehicle.model");
const { DB, connect_db } = require("../configs/Database");


module.exports = {
  // --- Vehicles 
  Vehicle,
  CreateVehicleModel,
  UpdateVehicleModel,
  // ---
};
