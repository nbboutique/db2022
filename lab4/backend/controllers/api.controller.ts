import { NextFunction, Request, Response } from "express";
import { IProduct, Product } from "../models/product";
import { success, successWithData, HttpError } from "../models/responses";

export class ApiController {
  async getById(id: string, req: Request, res: Response, next: NextFunction) {
    const product = await Product.findById(id);

    if (product) {
      return successWithData(res, product);
    }

    return next(new HttpError(404, "Product not found"));
  }

  getAll(res: Response) {
    Product.find({}).then((tea) => {
      return successWithData(res, tea);
    });
  }

  async create(req: Request, res: Response, next: NextFunction) {
    if (req?.body) {
      const { body } = req;

      var product: IProduct = {
        ...body,
      };

      Product.insertMany([product]).then((createdTea) => {
        successWithData(res, createdTea);
      });
    } else {
      return next(new HttpError(400, "Invalid request"));
    }
  }

  async update(id: string, req: Request, res: Response, next: NextFunction) {
    if (req?.body) {
      const { body } = req;

      const updateTea: IProduct = { ...body };

      await Product.findByIdAndUpdate(id, updateTea);

      return success(res, 204);
    } else {
      return next(new HttpError(400, "Invalid request"));
    }
  }

  delete(id: string, res: Response) {
    Product.findByIdAndDelete(id).then(() => {
      return success(res, 204);
    });
  }
}
