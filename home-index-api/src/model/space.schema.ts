import { model, Schema, Model, Document } from 'mongoose'
import { Container } from './container.schema'

export interface Space extends Document {
  nickname: string
  description: string
  containers: Container[]
}

const SpaceSchema: Schema = new Schema({
  nickname: { type: String, required: true },
  description: { type: String },
  containers: { type: Schema.Types.ObjectId, ref: 'Container' }
})

export const SpaceModel: Model<Space> = model('Space', SpaceSchema)
