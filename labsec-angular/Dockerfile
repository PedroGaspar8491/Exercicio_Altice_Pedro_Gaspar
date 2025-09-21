FROM node:18-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install -g @angular/cli@16.2.5 && npm install
COPY . .
RUN ng build --configuration production

FROM nginx:alpine
COPY --from=build /app/dist/labsec-angular /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
