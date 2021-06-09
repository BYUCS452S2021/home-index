FROM node:14.16.0

WORKDIR /usr/src/app

COPY . .

RUN npm install \
&& npm run build

EXPOSE 3000/tcp

CMD ["npm", "run", "start"]
