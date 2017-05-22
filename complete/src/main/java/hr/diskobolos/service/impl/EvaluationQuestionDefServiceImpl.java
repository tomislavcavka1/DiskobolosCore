/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.diskobolos.service.impl;

import hr.diskobolos.model.evaluation.EvaluationQuestionDef;
import hr.diskobolos.persistence.IEvaluationQuestionDefPersistence;
import hr.diskobolos.service.IEvaluationQuestionDefService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomislav Čavka
 */
@Service
public class EvaluationQuestionDefServiceImpl implements IEvaluationQuestionDefService {

    @Autowired
    IEvaluationQuestionDefPersistence evaluationQuestionDefPersistence;

    @Override
    public void persist(EvaluationQuestionDef entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(EvaluationQuestionDef entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EvaluationQuestionDef findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(EvaluationQuestionDef entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(List<EvaluationQuestionDef> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EvaluationQuestionDef> findAll() {
        return evaluationQuestionDefPersistence.findAll();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
