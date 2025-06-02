const { Sequelize, DataTypes, Model } = require("sequelize");
const Joi = require("joi");
const { DB } = require("../configs/Database");


// id	UUID (PK)
// depart	STRING
// destination	STRING
// date_depart	DATE
// heure_depart	TIME
// heure_arrivee	TIME
// statut	ENUM('prévu', 'en_cours', 'terminé', 'annulé')
// vehicleId	UUID (FK → Vehicle)
// driverId	UUID (FK → Driver)
// createdAt	DATE
// updatedAt	DATE

const Trip = DB.define(
    "Trip",
    {
        id: {
            type: DataTypes.UUID,
            defaultValue: DataTypes.UUIDV4,
            primaryKey: true,
        },
        depart: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        destination: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        dateDepart: {
            type: DataTypes.DATEONLY,
            allowNull: false,
        },
        heureDepart: {
            type: DataTypes.TIME,
            allowNull: false,
        },
        heureArrivee: {
            type: DataTypes.TIME,
            allowNull: true,
        },
        statut: {
            type: DataTypes.ENUM("prévu", "en_cours", "terminé", "annulé"),
            allowNull: false,
            defaultValue: "prévu",
        },
    },
    {
        createdAt: "createdAt",
        updatedAt: "updatedAt",
        tableName: "trips",
    }
);

const CreateTripModel = Joi.object({
    depart: Joi.string().required(),
    destination: Joi.string().required(),
    dateDepart: Joi.date().iso().required(),
    heureDepart: Joi.string().pattern(/^\d{2}:\d{2}:\d{2}$/).required(),
    heureArrivee: Joi.string().pattern(/^\d{2}:\d{2}:\d{2}$/).optional().allow(null),
    statut: Joi.string().valid("prévu", "en_cours", "terminé", "annulé").default("prévu"),
});

const UpdateTripModel = Joi.object({
    depart: Joi.string().optional(),
    destination: Joi.string().optional(),
    dateDepart: Joi.date().iso().optional(),
    heureDepart: Joi.string().pattern(/^\d{2}:\d{2}:\d{2}$/).optional(),
    heureArrivee: Joi.string().pattern(/^\d{2}:\d{2}:\d{2}$/).optional().allow(null),
    statut: Joi.string().valid("prévu", "en_cours", "terminé", "annulé").optional(),
});

module.exports = {
    Trip,
    CreateTripModel,
    UpdateTripModel
};