# Use node image
FROM node:latest

# Create working directory
WORKDIR /usr/src/backend

# Install app dependencies
COPY package*.json ./

# Init JS app
RUN npm install

COPY . .

# Expose the port
EXPOSE 5019

# Run app
CMD [ "npm", "start" ]