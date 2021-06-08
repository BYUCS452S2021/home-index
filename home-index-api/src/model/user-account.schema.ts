import { model, Schema, Model, Document } from 'mongoose'
import { Property } from './property.schema'

export interface UserAccount extends Document {
  username: string
  firstname: string
  lastname: string
  properties: Property[]
}

const UserAccountSchema: Schema = new Schema({
  username: { type: String, required: true, index: true },
  firstname: { type: String, required: true },
  lastname: { type: String, required: true },
  properties: { type: Schema.Types.ObjectId, ref: 'Property' }
})

export const UserAccountModel: Model<UserAccount> = model('UserAccount', UserAccountSchema)
