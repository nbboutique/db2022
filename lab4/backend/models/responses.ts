import { Response } from "express";

export function successWithData<TData>(res: Response, data: TData, status: number = 200) {
  res.status(status).json(data);
}

export function success(res: Response, status: number = 200) {
  res.status(status).json();
}

export class HttpError extends Error {
  status: number;
  message: string;

  constructor(status: number, message: string) {
    super(message);
    this.status = status;
    this.message = message;
  }
}
