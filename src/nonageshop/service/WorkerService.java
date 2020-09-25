package nonageshop.service;

import nonageshop.dao.WorkerDao;
import nonageshop.dao.impl.WorkerDaoImpl;
import nonageshop.dto.Worker;

public class WorkerService {

	private WorkerDao dao = WorkerDaoImpl.getInstance();
	
	public int workerCheck(Worker worker) {
		return dao.workerCheck(worker);
	}
	
}
