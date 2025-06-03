const axios = require('axios');
const os = require('os');

// Informations pour Eureka
const PORT = 5000;
const eurekaHost = 'discovery';
const eurekaPort = 8761;
const appName = 'fleet-service';
const instanceId = `${os.hostname()}:${appName}:${PORT}`;

// Déclaration JSON
const registration = {
    instance: {
        vipAddress: appName,
        instanceId: instanceId,
        hostName: os.hostname(),
        app: appName,
        ipAddr: '127.0.0.1',
        status: 'UP',
        port: { "$": PORT, "@enabled": true },
        dataCenterInfo: {
            "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
            name: "MyOwn"
        }
    }
};

// Enregistrement dans Eureka
const registerWithEureka = async () => {
    try {
        await axios.post(`http://${eurekaHost}:${eurekaPort}/eureka/apps/${appName}`, registration, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        console.log('✅ Service enregistré dans Eureka');
    } catch (err) {
        console.error('❌ Erreur lors de l\'enregistrement dans Eureka :', err.message);
    }
};

// Send heartbeat to Eureka
const sendHeartbeat = () => {
    axios.put(`http://${eurekaHost}:${eurekaPort}/eureka/apps/${appName}/${instanceId}`)
        .then(() => console.log('💓 Heartbeat envoyé à Eureka'))
        .catch(err => {
            console.error('❌ Erreur lors de l\'envoi du heartbeat à Eureka :', err.message);
            if (err.response && err.response.status === 404) {
                console.error('⚠️ Service non trouvé dans Eureka, tentative de réenregistrement...');
                registerWithEureka();
            }
        });
};

module.exports = {
    registerWithEureka,
    sendHeartbeat
};
