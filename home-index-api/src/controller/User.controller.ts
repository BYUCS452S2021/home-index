import { User, UserModel } from '../model/item.schema'

export async function readAllUsers(): Promise<User[]> {
  return await UserModel.find()
}

export async function createUser(user: User): Promise<User> {
  let userModel = new UserModel()
  userModel.set(user)
  await userModel.save()
  return userModel
}
