import express from 'express'
import { config as dotenvConfig } from 'dotenv'
import dotenvExpand from 'dotenv-expand'
import { router } from './router'
import mongoose from 'mongoose'

const env = dotenvConfig()
dotenvExpand(env)

const app = express()
app.use(express.json())
app.use('/', router)

mongoose.connect(process.env.EXPRESS_MONGO_URL as string, {
  useNewUrlParser: true,
  useUnifiedTopology: true,
  useCreateIndex: true
})

app.listen(process.env.EXPRESS_PORT, () => {
  console.log(`The application is listening on port ${process.env.EXPRESS_PORT}!`)
})
