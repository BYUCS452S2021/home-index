import { model, Schema, Model, Document } from 'mongoose'
import { Item } from './item.schema'

export interface Container extends Document {
  nickname: string
  description: string
  items: Item[]
}

const ContainerSchema: Schema = new Schema({
  nicname: { type: String, required: true },
  description: { type: String, required: true },
  items: { type: Schema.Types.ObjectId, ref: 'Item' }
})

export const ContainerModel: Model<Container> = model('Container', ContainerSchema)
