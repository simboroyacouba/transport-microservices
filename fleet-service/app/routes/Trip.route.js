const express = require("express");

const tripRouter = express.Router();

const {
    getAllTrips,
    createTrip,
    updateTrip,
    deleteTrip,
    getTripById
} = require("../controllers/Trip.controller");

// Components for Swagger documentation
/**
 * @swagger
 * components:
 *   schemas:
 *     CreateTripModel:
 *       type: object
 *       properties:
 *         depart:
 *           type: string
 *           description: Lieu de départ
 *         destination:
 *           type: string
 *           description: Lieu de destination
 *         dateDepart:
 *           type: string
 *           format: date
 *           description: Date de départ (YYYY-MM-DD)
 *         heureDepart:
 *           type: string
 *           pattern: '^\\d{2}:\\d{2}:\\d{2}$'
 *           description: Heure de départ (HH:mm:ss)
 *         heureArrivee:
 *           type: string
 *           pattern: '^\\d{2}:\\d{2}:\\d{2}$'
 *           description: Heure d'arrivée (HH:mm:ss)
 *         statut:
 *           type: string
 *           enum: [prévu, en_cours, terminé, annulé]
 *           default: prévu
 *         vehicleId:
 *           type: string
 *           format: uuid
 *           description: ID du véhicule
 *         driverId:
 *           type: string
 *           format: uuid
 *           description: ID du conducteur
 *       required:
 *         - depart
 *         - destination
 *         - dateDepart
 *         - heureDepart
 *         - statut
 *     UpdateTripModel:
 *       type: object
 *       properties:
 *         depart:
 *           type: string
 *         destination:
 *           type: string
 *         dateDepart:
 *           type: string
 *           format: date
 *         heureDepart:
 *           type: string
 *           pattern: '^\\d{2}:\\d{2}:\\d{2}$'
 *         heureArrivee:
 *           type: string
 *           pattern: '^\\d{2}:\\d{2}:\\d{2}$'
 *         statut:
 *           type: string
 *           enum: [prévu, en_cours, terminé, annulé]
 *         vehicleId:
 *           type: string
 *           format: uuid
 *         driverId:
 *           type: string
 *           format: uuid
 */

/**
 * @swagger
 * tags:
 *   name: Trip
 *   description: Gestion des trajets
 */

/**
 * @swagger
 * /trip:
 *   get:
 *     summary: Récupérer tous les trajets
 *     tags: [Trip]
 *     responses:
 *       200:
 *         description: Liste des trajets
 */
tripRouter.get("/", getAllTrips);

/**
 * @swagger
 * /trip/{id}:
 *   get:
 *     summary: Récupérer un trajet par ID
 *     tags: [Trip]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         description: ID du trajet à récupérer
 *         schema:
 *           type: string
 *     responses:
 *       200:
 *         description: Un trajet
 *       404:
 *         description: Trajet non trouvé
 */
tripRouter.get("/:id", getTripById);

/**
 * @swagger
 * /trip/{id}:
 *   delete:
 *     summary: Supprimer un trajet par ID
 *     tags: [Trip]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         description: ID du trajet à supprimer
 *         schema:
 *           type: string
 *     responses:
 *       204:
 *         description: Trajet supprimé avec succès
 *       404:
 *         description: Trajet non trouvé
 */
tripRouter.delete("/:id", deleteTrip);

/**
 * @swagger
 * /trip:
 *   post:
 *     summary: Créer un nouveau trajet
 *     tags: [Trip]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/CreateTripModel'
 *     responses:
 *       201:
 *         description: Trajet créé avec succès
 */
tripRouter.post("/", createTrip);

/**
 * @swagger
 * /trip/{id}:
 *   patch:
 *     summary: Mettre à jour un trajet par ID
 *     tags: [Trip]
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         description: ID du trajet à mettre à jour
 *         schema:
 *           type: string
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/UpdateTripModel'
 *     responses:
 *       200:
 *         description: Trajet mis à jour avec succès
 */
tripRouter.patch("/:id", updateTrip);

module.exports = tripRouter;