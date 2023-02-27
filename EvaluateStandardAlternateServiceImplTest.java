package SampleTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import Sample.ProcessStandardRuleServiceImpl;
import com.rxtransaction.claims.dispensinglimits.dao.DlsStandardRuleDao;
import com.rxtransaction.claims.dispensinglimits.model.ClaimType;
import com.rxtransaction.claims.dispensinglimits.model.ClaimTypeData;
import com.rxtransaction.claims.dispensinglimits.model.DlStandardAlternateDetails;
import com.rxtransaction.claims.dispensinglimits.model.DlStandardMaintenanceDetails;
import com.rxtransaction.claims.dispensinglimits.model.DlStandardOverallDetails;
import com.rxtransaction.claims.dispensinglimits.service.EvaluateStandardAlternateService;
import com.rxtransaction.claims.dispensinglimits.service.EvaluateStandardMaintenanceService;
import com.rxtransaction.claims.dispensinglimits.service.EvaluateStandardOverallService;
import com.rxtransaction.claims.dispensinglimits.service.ProcessClaimService;
import com.rxtransaction.claimsprocessingsupport.model.processing.ClaimsProcessingDetails;
import com.rxtransaction.claimsprocessingsupport.model.processing.CoverageCodeDetails;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EvaluateStandardAlternateServiceImplTest {

    @InjectMocks private EvaluateStandardAlternateServiceImplTest evaluateStandardAlternateServiceImplTest;

    @Mock private EvaluateListCriteriaService evaluateListCriteriaService;

    @Mock private DlStandardAlternateDetails dlStandardAlternatedetails;

    @Mock private ClaimsProcessingDetails claimsProcessingDetails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(
            "ProcessWhen")
    public void testProcess() {

        DlStandardAlternateDetails dlStandardAlternate = createDlStandardAlternateDetailsStub();
        ClaimsProcessingDetails claimsProcessingDetails = createClaimsProcessingDetailsStub();

        when(dlsStandardRuleDao.getDlStandardAlternate(claimsProcessingDetails))
                .thenReturn(Optional.of(dlStandardAlternate));
        when(evaluateListCriteriaService.process(dlStandardAlternate, claimsProcessingDetails))
                .thenReturn(true);
    }
    private ClaimsProcessingDetails createClaimsProcessingDetailsStub() {
        CoverageCodeDetails coverageCodeDetails = new CoverageCodeDetails();
        coverageCodeDetails.setDispensingLimitsBlockId(null);

        return ClaimsProcessingDetails.builder()
                .coverageCodeDetails(coverageCodeDetails)
                .approvedMessages(Collections.singletonList("Approved"))
                .build();
    }
    private DlStandardAlternateDetails createDlStandardAlternateDetailsStub() {
        return DlStandardAlternateDetails.builder()
                .beginDaysSupplyCnt(10)
                .claimReportingApplicationId(1L)
                .daysSupplyCnt(5)
                .drugListId(123L)
                .endDaysSupplyCnt(5)
                .pharmacyListId(111L)
                .pharmacyNetworkInd("network")
                .productListId(23L)
                .quantityLimit(new BigDecimal(2))
                .messageId(111L)
                .conditionId(1L)
                .build();
    }
}
