FROM node:23-slim

WORKDIR /app

COPY . .

RUN npm install --legacy-peer-deps  && npm install -g nodemon

CMD [ "npm", "run", "dev"]

EXPOSE 5000