const { Vehicle, CreateVehicleModel, UpdateVehicleModel, } = require("./Vehicle.model");
const { Driver, CreateDriverModel, UpdateDriverModel } = require("./Driver.model");
const { Trip, CreateTripModel, UpdateTripModel } = require("./Trip.model");
const { DB, connect_db } = require("../configs/Database");


// Relation entre les modèles
Vehicle.hasMany(Trip, { foreignKey: "vehicleId" });
Trip.belongsTo(Vehicle, { foreignKey: "vehicleId" });
Driver.hasMany(Trip, { foreignKey: "driverId" });
Trip.belongsTo(Driver, { foreignKey: "driverId" });


module.exports = {
  // --- Vehicles 
  Vehicle,
  CreateVehicleModel,
  UpdateVehicleModel,
  // --- Drivers
  Driver,
  CreateDriverModel,
  UpdateDriverModel,
  // --- Trips
  Trip,
  CreateTripModel,
  UpdateTripModel,
};
