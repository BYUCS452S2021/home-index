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

mongoose.connect('mongodb://localhost:27017', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
  useCreateIndex: true
})

app.listen(3000, () => {
  console.log(`The application is listening on port 3000!`)
})
