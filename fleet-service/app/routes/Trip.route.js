const express = require("express");

const tripRouter = express.Router();

const {
    getAllTrips,
    createTrip,
    updateTrip,
    deleteTrip,
    getTripById
} = require("../controllers/Trip.controller");

tripRouter.get("/", getAllTrips);
tripRouter.get("/:id", getTripById);
tripRouter.delete("/:id", deleteTrip);
tripRouter.post("/", createTrip);
tripRouter.patch("/:id", updateTrip);

module.exports = tripRouter;