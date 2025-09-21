# Use Node 18 LTS
FROM node:18-alpine

# Set working directory
WORKDIR /app

# Copy package.json and package-lock.json first (for caching)
COPY package*.json ./

# Install dependencies, including Angular CLI
RUN npm install -g @angular/cli@16.2.5 && npm install

# Copy the rest of the source code
COPY . .

# Expose port
EXPOSE 4200

# Run Angular dev server on all interfaces
CMD ["ng", "serve", "--host", "0.0.0.0"]
