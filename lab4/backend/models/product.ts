import { Schema, model } from "mongoose";

export interface IProduct {
  name: string;
  description: string;
  imageUrl: string;
  price: number;
}

const ProductScheme = new Schema(
  {
    name: { type: String, required: true },
    description: { type: String, required: true },
    imageUrl: { type: String, required: true },
    price: { type: Number, required: true },
  },
  {
    versionKey: false,
    collection: "Tea",
  }
);

const Product = model<IProduct>("Product", ProductScheme);
export { Product };
