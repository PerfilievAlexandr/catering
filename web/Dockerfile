FROM node:17-alpine
ENV NODE_ENV development
WORKDIR /usr/src/app
COPY package.json .
COPY yarn.lock .
RUN yarn install
COPY . .