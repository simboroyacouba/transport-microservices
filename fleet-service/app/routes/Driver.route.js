const express = require("express");

const driverRouter = express.Router();

const {
    getAllDrivers,
    createDriver,
    updateDriver,
    deleteDriver,
    getDriverById
} = require("../controllers/Driver.controller");

driverRouter.get("/", getAllDrivers);
driverRouter.get("/:id", getDriverById);
driverRouter.delete("/:id", deleteDriver);
driverRouter.post("/", createDriver);
driverRouter.patch("/:id", updateDriver);

module.exports = driverRouter;