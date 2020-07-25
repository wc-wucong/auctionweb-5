package com.accp.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.dao.PersonObjMapper;
import com.accp.pojo.PersonObj;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class PersonBiz {

	@Autowired
	private PersonObjMapper personDao;

	public PageInfo<PersonObj> findPersonListByPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<PersonObj>(personDao.queryAllPersons());
	}

	public PersonObj getPersonById(Integer pid) {
		return personDao.loadPerson(pid);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void removePersonById(Integer pid) {
		personDao.deletePerson(pid);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void modifyPerson(PersonObj person) {
		personDao.deletePerson(person.getPid());
		personDao.savePerson(person);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void addPerson(PersonObj person) {
		personDao.savePerson(person);
	}
}
