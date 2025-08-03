import {OperationModel} from './operation';

export interface AccountModel {
  accountId:   string;
  balance:     number;
  currentPage: number;
  totalPages:  number;
  pageSize:    number;
  operations:  OperationModel[];
}
