const express = require("express");

const driverRouter = express.Router();

const {
    getAllDrivers,
    createDriver,
    updateDriver,
    deleteDriver,
    getDriverById
} = require("../controllers/Driver.controller");



/**
 * @swagger
 * tags:
 *   name: Driver
 *   description: Driver management
 */
/**
 * @swagger
 * /driver:
 *   get:
 *     summary: Get all drivers
 *     tags: [Driver]
 *     responses:
 *       200:
 *         description: A list of drivers
 */
driverRouter.get("/", getAllDrivers);

/**
 * @swagger
 * /driver/{id}:
 *   get:
 *     summary: Get a driver by ID
 *     tags: [Driver]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         description: The ID of the driver to retrieve
 *         schema:
 *           type: string
 *     responses:
 *       200:
 *         description: A driver object
 *       404:
 *         description: Driver not found
 */
driverRouter.get("/:id", getDriverById);
driverRouter.delete("/:id", deleteDriver);
driverRouter.post("/", createDriver);
driverRouter.patch("/:id", updateDriver);

module.exports = driverRouter;