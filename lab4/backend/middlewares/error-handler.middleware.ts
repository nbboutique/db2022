import { NextFunction, Request, Response } from "express";
import { HttpError } from "../models/HttpError";

export function errorHandler(err: Error, req: Request, res: Response, next: NextFunction) {
  console.log(`API ERROR: ${err.message}`);
  if (err instanceof HttpError) {
    return res.status(err.status).json({error: err.message});
  } else {
    return res.status(500).json({error: err.message});
  }
}
