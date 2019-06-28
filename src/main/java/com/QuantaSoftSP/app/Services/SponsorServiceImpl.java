package com.QuantaSoftSP.app.Services;

import com.QuantaSoftSP.app.Dao.ISponsorDao;
import com.QuantaSoftSP.app.Entity.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SponsorServiceImpl  implements ISponsorService{
    @Autowired
    private ISponsorDao sponsorDao;

    @Override
    @Transactional(readOnly = true)
    public List<Sponsor> findAll() {
        return (List<Sponsor>) sponsorDao.findAll();
    }
    @Override
    public Sponsor findById(Long id) {
        return sponsorDao.findById(id).orElse(null);
    }
    @Override
    public Sponsor save(Sponsor sponsor) {
        return sponsorDao.save(sponsor);
    }
    @Override
    public void delete(Long id) {
        sponsorDao.deleteById(id);
    }
}
