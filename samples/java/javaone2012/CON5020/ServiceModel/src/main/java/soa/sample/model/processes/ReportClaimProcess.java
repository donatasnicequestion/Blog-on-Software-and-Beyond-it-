package soa.sample.model.processes;

import soa.sample.model.data.claims.activity.process.ReportClaimInput;
import soa.sample.model.data.claims.activity.process.ReportClaimReturn;
import soa.sample.model.faults.BusinessException;


public interface ReportClaimProcess {
    
    public ReportClaimReturn process(ReportClaimInput reportClaimInput) throws BusinessException;
    
}
