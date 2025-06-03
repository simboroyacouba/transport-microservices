const express = require("express");

const driverRouter = express.Router();

const {
    getAllDrivers,
    createDriver,
    updateDriver,
    deleteDriver,
    getDriverById
} = require("../controllers/Driver.controller");


// Components for Swagger documentation
/**
 * @swagger
 * components:
 *   schemas:
 *     CreateDriverModel:
 *       type: object
 *       properties:
 *         nom_prenom:
 *           type: string
 *           description: Full name of the driver
 *         numero_permis:
 *           type: string
 *           description: Driver's license number
 *         telephone:
 *           type: string
 *           description: Driver's phone number
 *         email:
 *           type: string
 *           format: email
 *           description: Driver's email address (optional)
 *         status:
 *           type: string
 *           enum: [active, inactive]
 *           default: active
 *       required:
 *         - nom_prenom
 *         - numero_permis
 *         - telephone
 *
 *     UpdateDriverModel:
 *       type: object
 *       properties:
 *         nom_prenom:
 *           type: string
 *         numero_permis:
 *           type: string
 *         telephone:
 *           type: string
 *         email:
 *           type: string
 *           format: email
 *         status:
 *           type: string
 *           enum: [active, inactive]
 */

/**
 * @swagger
 * tags:
 *   name: Driver
 *   description: Driver management
 */
// Get all drivers
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

// Get a driver by ID
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

// Delete a driver by ID
/**
 * @swagger
 * /driver/{id}:
 *   delete:
 *     summary: Delete a driver by ID
 *     tags: [Driver]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         description: The ID of the driver to delete
 *         schema:
 *           type: string
 *     responses:
 *       204:
 *         description: Driver deleted successfully
 *       404:
 *         description: Driver not found
 */
driverRouter.delete("/:id", deleteDriver);

// Create a new driver
/**
* @swagger
* /driver:
*   post:
*     summary: Create a new driver
*     tags: [Driver]
*     requestBody:
*       required: true
*       content:
*         application/json:
*           schema:
*             $ref: '#/components/schemas/CreateDriverModel'
*     responses:
*       201:
*         description: Driver created successfully
*/
driverRouter.post("/", createDriver);

// Update a driver by ID
/**
 * @swagger
 * /driver/{id}:
 *   patch:
 *     summary: Update a driver by ID
 *     tags: [Driver]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         description: The ID of the driver to update
 *         schema:
 *           type: string
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/UpdateDriverModel'
 *     responses:
 *       200:
 *         description: Driver updated successfully
 */
driverRouter.patch("/:id", updateDriver);

module.exports = driverRouter;