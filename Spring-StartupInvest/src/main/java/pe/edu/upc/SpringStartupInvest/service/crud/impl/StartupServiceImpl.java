package pe.edu.upc.SpringStartupInvest.service.crud.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.upc.SpringStartupInvest.model.entity.Startup;
import pe.edu.upc.SpringStartupInvest.model.repository.StartupRepository;
import pe.edu.upc.SpringStartupInvest.service.crud.StartupService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class StartupServiceImpl implements StartupService {

	@Autowired
	private StartupRepository startupRepository;

	@Override
	public JpaRepository<Startup, Integer> getJpaRepository() {
		return startupRepository;
	}

	@Override
	public Optional<Startup> findById(Integer id) throws Exception {
		return startupRepository.findById(id);
	}

	@Override
	public List<Startup> findByName(String name) throws Exception {
		return startupRepository.findByName(name);
	}

	@Override
	public List<Startup> findByState(Boolean state) throws Exception {
		return startupRepository.findByState(state);
	}

	@Override
	public List<Startup> findByDateRecently() throws Exception {
		return startupRepository.findByDateRecently();
	}

	@Override
	public List<Startup> findByDateBetween(Date date1, Date date2) throws Exception {
		return startupRepository.findByDateBetween(date1, date2);
	}

	@Override
	public double getAmounthInvestedById(int id) throws Exception {

		return startupRepository.getAmounthInvestedById(id);
	}

	@Override
	public Integer getPositionStartupById(int id) throws Exception {

		return startupRepository.getPositionStartupById(id);
	}

	@Override
	public List<Startup> listStartupsMostPopular() throws Exception {
		return startupRepository.listStartupsMostPopular();
	}

	/*
	 * @Override public List<Startup> findByPopular() throws Exception { return
	 * startupRepository.findByPopular(); }
	 */
}
