# ====== Stage 1 ======
# Prepare app to deployment

# Use node image
FROM node:latest as build

# Create working directory
WORKDIR /usr/local/app

# Add the source code to container
COPY ./ /usr/local/app/

# Init JS app
RUN npm install
RUN npm run build

# ====== Stage 2 ======
# Serve app with nginx

# Use nginx image
FROM nginx:latest

# Copy build to nginx image container
COPY --from=build /usr/local/app/dist/frontend /usr/share/nginx/html

# Expose the port
EXPOSE 80
