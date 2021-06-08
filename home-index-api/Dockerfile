FROM node:14.16.0

WORKDIR /usr/src/app

COPY . .

RUN yarn install \
&& yarn run build

EXPOSE 3000/tcp

CMD ["yarn", "run", "start"]
