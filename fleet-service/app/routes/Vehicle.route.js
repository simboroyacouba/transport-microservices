const express = require("express");

const vehicleRouter = express.Router();

const {
  getAllVehicle, createVehicle, updateVehicle, deleteVehicle, getVehicleById
} = require("../controllers/Vehicle.controller");

// Components for Swagger documentation
/**
 * @swagger
 * components:
 *   schemas:
 *     CreateVehicleModel:
 *       type: object
 *       properties:
 *         code:
 *           type: string
 *           description: Code unique du véhicule
 *         type:
 *           type: string
 *           enum: [bus, mini-bus, van, truck, car]
 *           description: Type de véhicule
 *         marque:
 *           type: string
 *           description: Marque du véhicule
 *         status:
 *           type: string
 *           enum: [available, in maintenance, on trip]
 *           description: Statut du véhicule
 *         currentLocation:
 *           type: object
 *           description: "Localisation actuelle (ex: { \"latitude\": 12.34, \"longitude\": 56.78 })"
 *         lastMaintenanceDate:
 *           type: string
 *           format: date
 *           description: Date de la dernière maintenance
 *         capacity:
 *           type: integer
 *           description: Capacité du véhicule
 *       required:
 *         - code
 *         - type
 *         - status
 *         - capacity
 *     UpdateVehicleModel:
 *       type: object
 *       properties:
 *         code:
 *           type: string
 *         type:
 *           type: string
 *           enum: [bus, mini-bus, van, truck, car]
 *         marque:
 *           type: string
 *         status:
 *           type: string
 *           enum: [available, in maintenance, on trip]
 *         currentLocation:
 *           type: object
 *         lastMaintenanceDate:
 *           type: string
 *           format: date
 *         capacity:
 *           type: integer
 */

/**
 * @swagger
 * tags:
 *   name: Vehicle
 *   description: Gestion des véhicules
 */

/**
 * @swagger
 * /vehicle:
 *   get:
 *     summary: Récupérer tous les véhicules
 *     tags: [Vehicle]
 *     responses:
 *       200:
 *         description: Liste des véhicules
 */
vehicleRouter.get("/", getAllVehicle);

/**
 * @swagger
 * /vehicle/{id}:
 *   get:
 *     summary: Récupérer un véhicule par ID
 *     tags: [Vehicle]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         description: ID du véhicule à récupérer
 *         schema:
 *           type: string
 *     responses:
 *       200:
 *         description: Un véhicule
 *       404:
 *         description: Véhicule non trouvé
 */
vehicleRouter.get("/:id", getVehicleById);

/**
 * @swagger
 * /vehicle/{id}:
 *   delete:
 *     summary: Supprimer un véhicule par ID
 *     tags: [Vehicle]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         description: ID du véhicule à supprimer
 *         schema:
 *           type: string
 *     responses:
 *       204:
 *         description: Véhicule supprimé avec succès
 *       404:
 *         description: Véhicule non trouvé
 */
vehicleRouter.delete("/:id", deleteVehicle);

/**
 * @swagger
 * /vehicle:
 *   post:
 *     summary: Créer un nouveau véhicule
 *     tags: [Vehicle]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/CreateVehicleModel'
 *     responses:
 *       201:
 *         description: Véhicule créé avec succès
 */
vehicleRouter.post("/", createVehicle);

/**
 * @swagger
 * /vehicle/{id}:
 *   patch:
 *     summary: Mettre à jour un véhicule par ID
 *     tags: [Vehicle]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         description: ID du véhicule à mettre à jour
 *         schema:
 *           type: string
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/UpdateVehicleModel'
 *     responses:
 *       200:
 *         description: Véhicule mis à jour avec succès
 */
vehicleRouter.patch("/:id", updateVehicle);

module.exports = vehicleRouter;
