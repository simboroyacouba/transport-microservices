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
        date_depart: {
            type: DataTypes.DATEONLY,
            allowNull: false,
        },
        heure_depart: {
            type: DataTypes.TIME,
            allowNull: false,
        },
        heure_arrivee: {
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
        createdAt: "created_at",
        updatedAt: "updated_at",
        tableName: "trips",
    }
);

const CreateTripModel = Joi.object({
    depart: Joi.string().required(),
    destination: Joi.string().required(),
    date_depart: Joi.date().iso().required(),
    heure_depart: Joi.string().pattern(/^\d{2}:\d{2}:\d{2}$/).required(),
    heure_arrivee: Joi.string().pattern(/^\d{2}:\d{2}:\d{2}$/).optional().allow(null),
    statut: Joi.string().valid("prévu", "en_cours", "terminé", "annulé").default("prévu"),
});

const UpdateTripModel = Joi.object({
    depart: Joi.string().optional(),
    destination: Joi.string().optional(),
    date_depart: Joi.date().iso().optional(),
    heure_depart: Joi.string().pattern(/^\d{2}:\d{2}:\d{2}$/).optional(),
    heure_arrivee: Joi.string().pattern(/^\d{2}:\d{2}:\d{2}$/).optional().allow(null),
    statut: Joi.string().valid("prévu", "en_cours", "terminé", "annulé").optional(),
});

module.exports = {
    Trip,
    CreateTripModel,
    UpdateTripModel
};