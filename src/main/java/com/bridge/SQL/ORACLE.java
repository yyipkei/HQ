package com.bridge.SQL;

/**
 * Created by Kei on 11/9/2015.
 */
public class ORACLE {

    public static String SalesUpdate;
    public static String SalesPsTxCount;
    public static String GoaPsTxCount;
    public static String InsertSalesDataLog;
    public static String ReturnUpdate;
    public static String MergeUpdate;
    public static String InsertMergeDataLog;
    public static String CouponPsTxCount;
    public static String StockResUpdDate;
    public static String InsertStockResDataLog;
    public static String StockResPsTxCount;
    public static String DepositUpdate;


    static {
        SalesUpdate = "Declare\n" +
                "  v_log_dt         TIMESTAMP(3);\n" +
                "  v_LAST_SYNC_TIME TIMESTAMP(3);\n" +
                "  v_MAX_BATCH      VARCHAR2(100);\n" +
                "  v_remark         varchar2(100);\n" +
                "BEGIN\n" +
                "  v_log_dt := SYSTIMESTAMP;\n" +
                "  \n" +
                "    SELECT NVL(max(BATCH_NO),0)\n" +
                "  INTO v_MAX_BATCH\n" +
                "  FROM data_update_log_pos ;\n" +
                "  \n" +
                "  --SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC WHERE remark = 'Sales';\n" +
                "  v_MAX_BATCH := v_MAX_BATCH + 1 ;\n" +
                "  v_remark := 'Sales' ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sadet' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sadet  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sadet' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sadet\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sadet', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                " \n" +
                "   SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='satender' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM satender  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'satender' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM satender\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'satender', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sahdr' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sahdr  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sahdr' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sahdr\n" +
                "    WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sahdr', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='saitdisc' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM saitdisc  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "    INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'saitdisc' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM saitdisc\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'saitdisc', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='satxdisc' ;\n" +
                "  SELECT MAX(LAST_UPD_DATE) INTO v_log_dt FROM satxdisc  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'satxdisc' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ',' \n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM satxdisc\n" +
                "    WHERE LAST_UPD_DATE BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and LAST_UPD_DATE >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'satxdisc', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sastaff' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sastaff  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sastaff' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sastaff\n" +
                "    WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sastaff', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sastaffitem' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sastaffitem  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "     INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sastaffitem' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no\n" +
                "    || ','\n" +
                "    || staff_id,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sastaffitem\n" +
                "    WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sastaffitem', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sareason' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sareason  ;\n" +
                "\n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "    INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sareason' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sareason\n" +
                " WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sareason', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "    \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sagwp' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sagwp  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "   INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sagwp' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sagwp\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sagwp', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sadesc' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sadesc  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "     INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sadesc' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sadesc\n" +
                "    WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sadesc', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sacard' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sacard  ;\n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sacard' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sacard\n" +
                "    WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sacard', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='savwp' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM savwp  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'savwp' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM savwp\n" +
                "    WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'savwp', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='saserial' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM saserial  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'saserial' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM saserial\n" +
                "    WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'saserial', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  /*\n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='saonlineshop' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM saonlineshop  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'saonlineshop' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM saonlineshop\n" +
                "    WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'saonlineshop', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  */\n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sa_delivery' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sa_delivery  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sa_delivery' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sa_delivery\n" +
                " WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sa_delivery', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sa_mr_item' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sa_mr_item  ;\n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sa_mr_item' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sa_mr_item\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sa_mr_item', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sadisc_ref' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sadisc_ref  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sadisc_ref' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sadisc_ref\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sadisc_ref', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sagoa_action' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sagoa_action  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sagoa_action' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sagoa_action\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sagoa_action', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sagoa_det' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sagoa_det  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "   INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sagoa_det' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sagoa_det\n" +
                "   WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sagoa_det', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "    \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sagoa_hdr' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sagoa_hdr  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "   INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sagoa_hdr' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sagoa_hdr\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sagoa_hdr', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "    SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sagoa_staff' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sagoa_staff  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "   INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sagoa_staff' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sagoa_staff\n" +
                " WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sagoa_staff', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  /*\n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='gr_org_det' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM gr_org_det  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "   INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'gr_org_det' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM gr_org_det\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'gr_org_det', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='gr_org_tender' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM gr_org_tender  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'gr_org_tender' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM gr_org_tender\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'gr_org_tender', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " */\n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='dphdr_desc' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM dphdr_desc  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'dphdr_desc' ,\n" +
                "    loc_code\n" +
                "    || ','\n" +
                "    || dp_no\n" +
                "    ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM dphdr_desc\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'dphdr_desc', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "   SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='pm_memo_map' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM pm_memo_map  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'pm_memo_map' ,\n" +
                "    FPOS_MEMO_NO\n" +
                "    ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM pm_memo_map\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'pm_memo_map', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "    SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='edc_settlement' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM edc_settlement  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'edc_settlement' ,\n" +
                "     tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no \n" +
                "    || ','\n" +
                "    || tx_amount\n" +
                "    || ','\n" +
                "    || bank_host\n" +
                "    || ','\n" +
                "    || status,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM edc_settlement\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'edc_settlement', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "    COMMIT WORK;\n" +
                "END;";

        SalesPsTxCount = "Declare\n" +
                "  v_entity_key VARCHAR2(100);\n" +
                "  v_cntSahdr NUMBER := 0;\n" +
                "  v_cntSadet NUMBER := 0;\n" +
                "  v_cntSatender NUMBER := 0;\n" +
                "  v_cntSatxdisc NUMBER := 0;\n" +
                "  v_cntSaitdisc NUMBER := 0;\n" +
                "  v_cntSareason NUMBER := 0;\n" +
                "  v_cntSastaff NUMBER := 0;\n" +
                "  v_cntSastaffitem NUMBER := 0;\n" +
                "  v_cntSagwp NUMBER := 0;\n" +
                "  v_cntSadesc NUMBER := 0;\n" +
                "  v_cntSacard NUMBER := 0;\n" +
                "  v_cntSavwp NUMBER := 0;\n" +
                "  v_cntSaserial NUMBER := 0;\n" +
                "  v_cntSaonlineshop NUMBER := 0;\n" +
                "  v_cntSadiscref NUMBER := 0;\n" +
                "  v_cntSamritem NUMBER := 0;\n" +
                "  v_cntSadelivery NUMBER := 0;\n" +
                "  v_cntGrorgdet NUMBER := 0;\n" +
                "  v_cntGrorgtender NUMBER := 0;\n" +
                "  v_chk NUMBER := 0;\n" +
                "  v_cnthvrtn NUMBER := 0;\n" +
                "  v_chkrtn NUMBER := 0;\n" +
                "  v_chkvoid NUMBER := 0;\n" +
                "  v_cntDphdr NUMBER := 0;\n" +
                "  v_cntDpdet NUMBER := 0;\n" +
                "  v_cntDppayment NUMBER := 0;\n" +
                "  v_cntDphdrdesc NUMBER := 0;\n" +
                "  v_cntSastafftxn NUMBER := 0;\n" +
                "  v_cntDktprtdtl NUMBER := 0;\n" +
                "  v_cntsadetchk NUMBER := 0;\n" +
                "  v_cntsatenderchk NUMBER := 0;\n" +
                "  v_cntstaffchk NUMBER := 0;\n" +
                "  v_cntstaffitemchk NUMBER := 0;\n" +
                "  v_cnthvsalesrtn NUMBER := 0;\n" +
                "  v_chkgex NUMBER := 0;\n" +
                "\n" +
                "Cursor c1\n" +
                "is\n" +
                "select entity_key \n" +
                "from data_update_log a\n" +
                "where a.entity_name ='sahdr'  \n" +
                "and is_comp ='A';\n" +
                "\n" +
                "begin\n" +
                "OPEN c1;\n" +
                "LOOP\n" +
                "    FETCH c1 INTO v_entity_key;    \n" +
                "    EXIT WHEN c1%NOTFOUND;  \n" +
                "    \n" +
                "  v_chk := 0;\n" +
                "  v_cnthvrtn :=0;\n" +
                "  v_chkrtn :=0;\n" +
                "  v_chkvoid:=0;\n" +
                "  v_cnthvsalesrtn:=0;\n" +
                "    \n" +
                "  select count(*) into v_cnthvrtn from sahdr a\n" +
                "  where a.entity_key = v_entity_key\n" +
                "  and a.tx_type ='02';\n" +
                "  \n" +
                "  if v_cnthvrtn > 0 then \n" +
                "  select count(*) into v_chkrtn from gr_org_det a\n" +
                "  where a.entity_key = v_entity_key;\n" +
                "   if v_chkrtn = 0 then \n" +
                "      v_chk := 1;\n" +
                "      update data_update_log set remark ='Fail gr_org_det'\n" +
                "      where entity_name ='sahdr' \n" +
                "      and entity_key = v_entity_key ;\n" +
                "    end if;\n" +
                "  end if;\n" +
                "  \n" +
                "  select count(*) into v_cnthvsalesrtn from sahdr a\n" +
                "  where a.entity_key = v_entity_key\n" +
                "  and a.tx_type in('01','02');\n" +
                "  \n" +
                "  if v_cnthvsalesrtn > 0 then\n" +
                "    v_cntsadetchk := 0;\n" +
                "    select count(*) into v_cntsadetchk from sadet a\n" +
                "    where a.entity_key = v_entity_key;\n" +
                "    if v_cntsadetchk = 0 then \n" +
                "        v_chk := 1;\n" +
                "        update data_update_log set remark ='Fail sadet'\n" +
                "        where entity_name ='sahdr' \n" +
                "        and entity_key = v_entity_key ;\n" +
                "    end if;\n" +
                "    \n" +
                "    v_cntsatenderchk := 0;\n" +
                "    select count(*) into v_cntsatenderchk from satender a\n" +
                "    where a.entity_key = v_entity_key;\n" +
                "    if v_cntsatenderchk = 0 then \n" +
                "     select count(*) into v_chkgex from sahdr a\n" +
                "     where a.entity_key = v_entity_key\n" +
                "     and a.tx_type in('01','02')\n" +
                "     and a.gex ='Y';\n" +
                "      if v_chkgex = 0 then \n" +
                "          v_chk := 1;\n" +
                "          update data_update_log set remark ='Fail satender'\n" +
                "          where entity_name ='sahdr' \n" +
                "          and entity_key = v_entity_key ;\n" +
                "       end if;  \n" +
                "    end if;\n" +
                "    \n" +
                "    v_cntstaffchk := 0;\n" +
                "    select count(*) into v_cntstaffchk from sastaff a\n" +
                "    where a.entity_key = v_entity_key;\n" +
                "    if v_cntstaffchk = 0 then \n" +
                "        v_chk := 1;\n" +
                "        update data_update_log set remark ='Fail sastaff'\n" +
                "        where entity_name ='sahdr' \n" +
                "        and entity_key = v_entity_key ;\n" +
                "    end if;\n" +
                "    \n" +
                "    v_cntstaffitemchk := 0;\n" +
                "    select count(*) into v_cntstaffitemchk from sastaffitem a\n" +
                "    where a.entity_key = v_entity_key;\n" +
                "    if v_cntstaffitemchk = 0 then \n" +
                "        v_chk := 1;\n" +
                "        update data_update_log set remark ='Fail sastaffitem'\n" +
                "        where entity_name ='sahdr' \n" +
                "        and entity_key = v_entity_key ;\n" +
                "    end if;\n" +
                "  end if;\n" +
                "\n" +
                "  /*v_chk := 0;\n" +
                "  v_cnthvrtn :=0;\n" +
                "  v_chkrtn :=0;*/\n" +
                "  \n" +
                "  select count(*) into v_cnthvrtn from saitdisc a,sahdr b\n" +
                "  where a.entity_key = v_entity_key\n" +
                "  and a.disc_id in('LC99','LC82','LC81','LC80','11','12','13','14','15','16','17','18')\n" +
                "  and a.entity_key = b.entity_key\n" +
                "  and b.gex = 'N';\n" +
                "  \n" +
                "  if v_cnthvrtn > 0 then \n" +
                "  select count(*) into v_chkrtn from SASTAFFTXN a\n" +
                "  where a.entity_key = v_entity_key;\n" +
                "   if v_chkrtn = 0 then \n" +
                "      v_chk := 1;\n" +
                "      update data_update_log set remark ='Fail Sastafftxn'\n" +
                "      where entity_name ='sahdr' \n" +
                "      and entity_key = v_entity_key ;\n" +
                "    end if;\n" +
                "  end if;\n" +
                "  \n" +
                "  /*v_chk := 0;\n" +
                "  v_cnthvrtn :=0;\n" +
                "  v_chkrtn :=0;*/\n" +
                "  \n" +
                "  select count(*) into v_cnthvrtn from sahdr a\n" +
                "  where a.entity_key = v_entity_key\n" +
                "  and a.tx_type ='03';\n" +
                "  \n" +
                "  if v_cnthvrtn > 0 then \n" +
                "  select count(*) into v_chkrtn from dphdr a\n" +
                "  where a.entity_key = v_entity_key;\n" +
                "   if v_chkrtn = 0 then \n" +
                "      v_chk := 1;\n" +
                "      update data_update_log set remark ='Fail dphdr'\n" +
                "      where entity_name ='sahdr' \n" +
                "      and entity_key = v_entity_key ;\n" +
                "    end if;\n" +
                "  end if;\n" +
                "  \n" +
                "  /*v_chk := 0;\n" +
                "  v_cnthvrtn :=0;\n" +
                "  v_chkrtn :=0;*/\n" +
                "  \n" +
                "  select count(*) into v_cnthvrtn from satender a\n" +
                "  where a.entity_key = v_entity_key\n" +
                "  and a.tender ='12';\n" +
                "  \n" +
                "  if v_cnthvrtn > 0 then \n" +
                "  select count(*) into v_chkrtn from dpdet a\n" +
                "  where a.entity_key = v_entity_key;\n" +
                "   if v_chkrtn = 0 then \n" +
                "      v_chk := 1; \n" +
                "      update data_update_log set remark ='Fail dpdet'\n" +
                "      where entity_name ='sahdr' \n" +
                "      and entity_key = v_entity_key ;\n" +
                "    end if;\n" +
                "  end if;\n" +
                "  \n" +
                " /* DBMS_OUTPUT.PUT_LINE('v_cnthvrtn:'||v_cnthvrtn||' is the new value'); \n" +
                "  DBMS_OUTPUT.PUT_LINE('v_chk:'||v_chk||' is the new value'); */\n" +
                "  \n" +
                "  select count(*) into v_chkvoid from sahdr a\n" +
                "  where a.entity_key = v_entity_key\n" +
                "  and a.void_by = a.CASHIER_NO\n" +
                "  and void_by is not null;\n" +
                "  \n" +
                "  if v_chkvoid > 0 then \n" +
                "    v_chk := 1;\n" +
                "    update data_update_log set remark ='Fail void'\n" +
                "    where entity_name ='sahdr' \n" +
                "    and entity_key = v_entity_key ;\n" +
                "   end if;\n" +
                "  \n" +
                "if v_chk = 0 then\n" +
                "/*\n" +
                "select count(*) into v_cntSahdr from sahdr a \n" +
                "where a.entity_key = v_entity_key;\n" +
                "*/\n" +
                "select count(*) into v_cntSadet from sadet a \n" +
                "where a.entity_key = v_entity_key;\n" +
                "\n" +
                "if v_cntSadet > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'sadet',\n" +
                "    v_cntSadet);\n" +
                "end if; \n" +
                "\n" +
                "select count(*) into v_cntSatender  from satender a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSatender > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'satender',\n" +
                "    v_cntSatender);\n" +
                "end if; \n" +
                "\n" +
                "select count(*) into v_cntSaitdisc  from saitdisc a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSaitdisc > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'saitdisc',\n" +
                "    v_cntSaitdisc);\n" +
                "end if; \n" +
                "\n" +
                "select count(*) into v_cntSatxdisc  from satxdisc a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSatxdisc > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'satxdisc',\n" +
                "    v_cntSatxdisc);\n" +
                "end if; \n" +
                "\n" +
                "select count(*) into v_cntSareason  from sareason a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSareason > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'sareason',\n" +
                "    v_cntSareason);\n" +
                "end if; \n" +
                "\n" +
                "\n" +
                "select count(*) into v_cntSastaff  from sastaff a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSastaff > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'sastaff',\n" +
                "    v_cntSastaff);\n" +
                "end if; \n" +
                "\n" +
                "select count(*) into v_cntSastaffitem  from sastaffitem a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSastaffitem > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'sastaffitem',\n" +
                "    v_cntSastaffitem);\n" +
                "end if; \n" +
                "\n" +
                "select count(*) into v_cntSagwp  from sagwp a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSagwp > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'sagwp',\n" +
                "    v_cntSagwp);\n" +
                "end if;\n" +
                "\n" +
                "select count(*) into v_cntSadesc  from sadesc a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSadesc > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'sadesc',\n" +
                "    v_cntSadesc);\n" +
                "end if;\n" +
                "\n" +
                "select count(*) into v_cntSacard  from sacard a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSacard > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'sacard',\n" +
                "    v_cntSacard);\n" +
                "end if;\n" +
                "\n" +
                "select count(*) into v_cntSavwp  from savwp a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSavwp > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'savwp',\n" +
                "    v_cntSavwp);\n" +
                "end if;\n" +
                "\n" +
                "select count(*) into v_cntSaserial  from saserial a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSaserial > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'saserial',\n" +
                "    v_cntSaserial);\n" +
                "end if;\n" +
                "\n" +
                "\n" +
                "select count(*) into v_cntSaonlineshop  from saonlineshop a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSaonlineshop > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'saonlineshop',\n" +
                "    v_cntSaonlineshop);\n" +
                "end if;\n" +
                "\n" +
                "select count(*) into v_cntSadiscref  from sadisc_ref a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSadiscref > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'sadisc_ref',\n" +
                "    v_cntSadiscref);\n" +
                "end if;\n" +
                "\n" +
                "\n" +
                "select count(*) into v_cntSamritem  from sa_mr_item a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSamritem > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'sa_mr_item',\n" +
                "    v_cntSamritem);\n" +
                "end if;\n" +
                "\n" +
                "select count(*) into v_cntSadelivery  from sa_delivery a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSadelivery > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'sa_delivery',\n" +
                "    v_cntSadelivery);\n" +
                "end if;\n" +
                "\n" +
                "select count(*) into v_cntGrorgdet  from gr_org_det a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntGrorgdet > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'gr_org_det',\n" +
                "    v_cntGrorgdet);\n" +
                "end if;\n" +
                "\n" +
                "select count(*) into v_cntGrorgtender  from gr_org_tender a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntGrorgtender > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'gr_org_tender',\n" +
                "    v_cntGrorgtender);\n" +
                "end if;\n" +
                "\n" +
                "select count(*) into v_cntDphdr  from dphdr a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntDphdr > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'dphdr',\n" +
                "    v_cntDphdr);\n" +
                "end if;\n" +
                "\n" +
                "select count(*) into v_cntDpdet  from dpdet a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntDpdet > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'dpdet',\n" +
                "    v_cntDpdet);\n" +
                "end if;\n" +
                "\n" +
                "select count(*) into v_cntDppayment  from DPPAYMENT a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntDppayment > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'dppayment',\n" +
                "    v_cntDppayment);\n" +
                "end if;\n" +
                "\n" +
                "select count(*) into v_cntSastafftxn from sastafftxn a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntSastafftxn > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'sastafftxn',\n" +
                "    v_cntSastafftxn);\n" +
                "end if;\n" +
                "\n" +
                "select count(*) into v_cntDktprtdtl from dkt_prt_det a \n" +
                "where a.entity_key = v_entity_key ;\n" +
                "\n" +
                "if v_cntDktprtdtl > 0 then \n" +
                "INSERT\n" +
                "INTO PS_TX_COUNT\n" +
                "  (\n" +
                "    ENTITY_NAME,\n" +
                "    ENTITY_KEY,\n" +
                "    ENTITY_UPD_DT,\n" +
                "    LOG_DT,\n" +
                "    IS_COMP,\n" +
                "    CHILD_NAME,\n" +
                "    CHILD_COUNT)\n" +
                "   VALUES\n" +
                "  ( 'sahdr',\n" +
                "    v_entity_key,\n" +
                "    systimestamp,\n" +
                "    systimestamp,\n" +
                "    0,\n" +
                "    'dkt_prt_det',\n" +
                "    v_cntDktprtdtl);\n" +
                "end if;\n" +
                "  \n" +
                "  update data_update_log set remark ='logged',is_comp = 0\n" +
                "  where entity_name ='sahdr' \n" +
                "  and entity_key = v_entity_key ;\n" +
                "  \n" +
                "  update sahdr set is_log = 1 \n" +
                "  where entity_key = v_entity_key ;\n" +
                " \n" +
                "  end if; \n" +
                "\n" +
                "  END LOOP;\n" +
                "  CLOSE c1;\n" +
                "  COMMIT ;  \n" +
                "end;";

        GoaPsTxCount = "Declare\n" +
                "v_goa_entity_key VARCHAR2(100);\n" +
                "v_cntSagoadet NUMBER := 0;\n" +
                "v_cntSagoastaff NUMBER := 0;\n" +
                "v_cntSagoatxn NUMBER := 0;\n" +
                "v_cntSagoaaction NUMBER := 0;\n" +
                "v_cntGoadktprtdtl NUMBER := 0;\n" +
                "v_goachk NUMBER := 0;\n" +
                "\n" +
                "Cursor c2\n" +
                "is\n" +
                "select entity_key \n" +
                "from data_update_log a\n" +
                "where a.entity_name ='sagoa_hdr'  \n" +
                "and is_comp ='A';\n" +
                "\n" +
                "begin\n" +
                "OPEN c2;\n" +
                "LOOP\n" +
                "    FETCH c2 INTO v_goa_entity_key;    \n" +
                "    EXIT WHEN c2%NOTFOUND;  \n" +
                "    \n" +
                "    v_goachk := 0; \n" +
                "    /*\n" +
                "    select count(*) into v_goachk from SAGOA_TXN a\n" +
                "    where a.entity_key = v_goa_entity_key;\n" +
                "    \n" +
                "     if v_goachk = 0 then \n" +
                "        update data_update_log set remark ='Fail SAGOA_TXN'\n" +
                "        where entity_name ='sagoa_hdr' \n" +
                "        and entity_key = v_goa_entity_key ;\n" +
                "    else\n" +
                "    */\n" +
                "    select count(*) into v_cntSagoadet from SAGOA_DET a \n" +
                "    where a.entity_key = v_goa_entity_key;\n" +
                "    \n" +
                "    if v_cntSagoadet > 0 then \n" +
                "    INSERT\n" +
                "    INTO PS_TX_COUNT\n" +
                "      (\n" +
                "        ENTITY_NAME,\n" +
                "        ENTITY_KEY,\n" +
                "        ENTITY_UPD_DT,\n" +
                "        LOG_DT,\n" +
                "        IS_COMP,\n" +
                "        CHILD_NAME,\n" +
                "        CHILD_COUNT)\n" +
                "       VALUES\n" +
                "      ( 'sagoa_hdr',\n" +
                "        v_goa_entity_key,\n" +
                "        systimestamp,\n" +
                "        systimestamp,\n" +
                "        0,\n" +
                "        'sagoa_det',\n" +
                "        v_cntSagoadet);\n" +
                "    end if; \n" +
                "    \n" +
                "    /*select count(*) into v_cntSagoastaff from SAGOA_staff a \n" +
                "    where a.entity_key = v_goa_entity_key;\n" +
                "    \n" +
                "    if v_cntSagoastaff > 0 then \n" +
                "    INSERT\n" +
                "    INTO PS_TX_COUNT\n" +
                "      (\n" +
                "        ENTITY_NAME,\n" +
                "        ENTITY_KEY,\n" +
                "        ENTITY_UPD_DT,\n" +
                "        LOG_DT,\n" +
                "        IS_COMP,\n" +
                "        CHILD_NAME,\n" +
                "        CHILD_COUNT)\n" +
                "       VALUES\n" +
                "      ( 'sagoa_hdr',\n" +
                "        v_goa_entity_key,\n" +
                "        systimestamp,\n" +
                "        systimestamp,\n" +
                "        0,\n" +
                "        'sagoa_staff',\n" +
                "        v_cntSagoastaff);\n" +
                "    end if; */\n" +
                "    \n" +
                "    select count(*) into v_cntSagoatxn from SAGOA_TXN a \n" +
                "    where a.entity_key = v_goa_entity_key;\n" +
                "    \n" +
                "    if v_cntSagoatxn > 0 then \n" +
                "    INSERT\n" +
                "    INTO PS_TX_COUNT\n" +
                "      (\n" +
                "        ENTITY_NAME,\n" +
                "        ENTITY_KEY,\n" +
                "        ENTITY_UPD_DT,\n" +
                "        LOG_DT,\n" +
                "        IS_COMP,\n" +
                "        CHILD_NAME,\n" +
                "        CHILD_COUNT)\n" +
                "       VALUES\n" +
                "      ( 'sagoa_hdr',\n" +
                "        v_goa_entity_key,\n" +
                "        systimestamp,\n" +
                "        systimestamp,\n" +
                "        0,\n" +
                "        'sagoa_txn',\n" +
                "        v_cntSagoatxn);\n" +
                "    end if; \n" +
                "    \n" +
                "    select count(*) into v_cntSagoaaction from SAGOA_ACTION a \n" +
                "    where a.entity_key = v_goa_entity_key;\n" +
                "    \n" +
                "    if v_cntSagoaaction > 0 then \n" +
                "    INSERT\n" +
                "    INTO PS_TX_COUNT\n" +
                "      (\n" +
                "        ENTITY_NAME,\n" +
                "        ENTITY_KEY,\n" +
                "        ENTITY_UPD_DT,\n" +
                "        LOG_DT,\n" +
                "        IS_COMP,\n" +
                "        CHILD_NAME,\n" +
                "        CHILD_COUNT)\n" +
                "       VALUES\n" +
                "      ( 'sagoa_hdr',\n" +
                "        v_goa_entity_key,\n" +
                "        systimestamp,\n" +
                "        systimestamp,\n" +
                "        0,\n" +
                "        'sagoa_action',\n" +
                "        v_cntSagoaaction);\n" +
                "    end if; \n" +
                "    \n" +
                "    select count(*) into v_cntGoadktprtdtl from dkt_prt_det a \n" +
                "    where a.entity_key = v_goa_entity_key ;\n" +
                "    \n" +
                "    if v_cntGoadktprtdtl > 0 then \n" +
                "    INSERT\n" +
                "    INTO PS_TX_COUNT\n" +
                "      (\n" +
                "        ENTITY_NAME,\n" +
                "        ENTITY_KEY,\n" +
                "        ENTITY_UPD_DT,\n" +
                "        LOG_DT,\n" +
                "        IS_COMP,\n" +
                "        CHILD_NAME,\n" +
                "        CHILD_COUNT)\n" +
                "       VALUES\n" +
                "      ( 'sagoa_hdr', \n" +
                "        v_goa_entity_key,\n" +
                "        systimestamp,\n" +
                "        systimestamp,\n" +
                "        0,\n" +
                "        'dkt_prt_det',\n" +
                "        v_cntGoadktprtdtl);\n" +
                "    end if;\n" +
                "    \n" +
                "     update data_update_log set remark ='logged',is_comp = 0\n" +
                "     where entity_name ='sagoa_hdr' \n" +
                "     and entity_key = v_goa_entity_key ;\n" +
                "      \n" +
                "     update sagoa_hdr set is_log = 1 \n" +
                "     where entity_key = v_goa_entity_key ;\n" +
                "      \n" +
                "  -- end if;     \n" +
                "      \n" +
                "  END LOOP;\n" +
                "  CLOSE c2;\n" +
                "  COMMIT ;\n" +
                "end;";

        InsertSalesDataLog = "Declare\n" +
                "  v_log_dt         TIMESTAMP(3);\n" +
                "  v_LAST_SYNC_TIME TIMESTAMP(3);\n" +
                "  v_MAX_BATCH      VARCHAR2(100);\n" +
                "BEGIN\n" +
                "  \n" +
                "  SELECT NVL(max(BATCH_NO),0)\n" +
                "  INTO v_MAX_BATCH\n" +
                "  FROM DATA_UPDATE_LOG ;\n" +
                "  \n" +
                "  v_MAX_BATCH := v_MAX_BATCH + 1 ;\n" +
                "  \n" +
                "  v_LAST_SYNC_TIME := null;\n" +
                "  v_log_dt := null;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_SYNC where TABLE_NAME ='sahdr' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sahdr  ;\n" +
                " \n" +
                "IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null and v_log_dt > v_LAST_SYNC_TIME then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sahdr' ,\n" +
                "    entity_key ,\n" +
                "    v_log_dt ,\n" +
                "    systimestamp ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'A' ,\n" +
                "    NULL\n" +
                "  FROM sahdr\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "\n" +
                "  INSERT INTO DATA_UPDATE_LOG_SYNC\n" +
                "    (Table_name , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sahdr', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "   v_LAST_SYNC_TIME := null;\n" +
                "  v_log_dt := null;\n" +
                " \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_SYNC where TABLE_NAME ='edc_settlement' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM edc_settlement  ;\n" +
                " \n" +
                "IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null and v_log_dt > v_LAST_SYNC_TIME then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'edc_settlement' ,\n" +
                "    entity_key ,\n" +
                "    v_log_dt ,\n" +
                "    systimestamp ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    0 ,\n" +
                "    NULL\n" +
                "  FROM edc_settlement\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "\n" +
                "  INSERT INTO DATA_UPDATE_LOG_SYNC\n" +
                "    (Table_name , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'edc_settlement', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "   v_LAST_SYNC_TIME := null;\n" +
                "  v_log_dt := null;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_SYNC where TABLE_NAME ='host_settlement' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM host_settlement  ;\n" +
                " \n" +
                "IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null and v_log_dt > v_LAST_SYNC_TIME then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'host_settlement' ,\n" +
                "    entity_key ,\n" +
                "    v_log_dt ,\n" +
                "    systimestamp ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    0 ,\n" +
                "    NULL\n" +
                "  FROM host_settlement\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "\n" +
                "  INSERT INTO DATA_UPDATE_LOG_SYNC\n" +
                "    (Table_name , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'host_settlement', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  /*\n" +
                "    v_LAST_SYNC_TIME := null;\n" +
                "  v_log_dt := null;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_SYNC where TABLE_NAME ='dphdr_desc' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM dphdr_desc  ;\n" +
                "\n" +
                "IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null and v_log_dt > v_LAST_SYNC_TIME then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'dphdr_desc' ,\n" +
                "    entity_key ,\n" +
                "    v_log_dt ,\n" +
                "    systimestamp ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    0 ,\n" +
                "    NULL\n" +
                "  FROM dphdr_desc\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "\n" +
                "  INSERT INTO DATA_UPDATE_LOG_SYNC\n" +
                "    (Table_name , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'dphdr_desc', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " */\n" +
                " \n" +
                " v_LAST_SYNC_TIME := null;\n" +
                " v_log_dt := null;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_SYNC where TABLE_NAME ='sagoa_hdr' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM SAGOA_HDR  ;\n" +
                " \n" +
                "IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null and v_log_dt > v_LAST_SYNC_TIME then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sagoa_hdr' ,\n" +
                "    entity_key ,\n" +
                "    v_log_dt ,\n" +
                "    systimestamp ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'A' ,\n" +
                "    NULL\n" +
                "  FROM SAGOA_HDR\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "\n" +
                "  INSERT INTO DATA_UPDATE_LOG_SYNC\n" +
                "    (Table_name , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sagoa_hdr', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "  COMMIT WORK;\n" +
                "END;";

        ReturnUpdate = "Declare\n" +
                "  v_log_dt         TIMESTAMP(3);\n" +
                "  v_LAST_SYNC_TIME TIMESTAMP(3);\n" +
                "  v_MAX_BATCH      VARCHAR2(100);\n" +
                "  v_remark         varchar2(100);\n" +
                "BEGIN\n" +
                "  v_log_dt := SYSTIMESTAMP;\n" +
                "  \n" +
                "  SELECT NVL(max(BATCH_NO),0)\n" +
                "  INTO v_MAX_BATCH\n" +
                "  FROM data_update_log_pos ;\n" +
                "  \n" +
                "  --SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC WHERE remark = 'Goodsreturn';\n" +
                "  v_MAX_BATCH := v_MAX_BATCH + 1 ;\n" +
                "  v_remark := 'Goodsreturn' ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='gr_org_det' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM gr_org_det  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "   INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'gr_org_det' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM gr_org_det\n" +
                "     WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'gr_org_det', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='gr_org_tender' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM gr_org_tender  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'gr_org_tender' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM gr_org_tender\n" +
                "     WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'gr_org_tender', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "    COMMIT WORK;\n" +
                "END;";

        MergeUpdate = "Declare\n" +
                "  v_log_dt         TIMESTAMP(3);\n" +
                "  v_LAST_SYNC_TIME TIMESTAMP(3);\n" +
                "  v_MAX_BATCH      VARCHAR2(100);\n" +
                "  v_remark         varchar2(100);\n" +
                "BEGIN\n" +
                "  v_log_dt := SYSTIMESTAMP;\n" +
                "  \n" +
                "  SELECT NVL(max(BATCH_NO),0)\n" +
                "  INTO v_MAX_BATCH\n" +
                "  FROM data_update_log_pos ;\n" +
                "\n" +
                "  v_MAX_BATCH := v_MAX_BATCH + 1 ;\n" +
                "  v_remark := 'Merge' ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='cafe_coupon_det' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM cafe_coupon_det  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "   INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'cafe_coupon_det' ,\n" +
                "   COUPON_NO ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM cafe_coupon_det\n" +
                "    WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'cafe_coupon_det', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='cafe_coupon_hdr' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM cafe_coupon_hdr  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "    INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'cafe_coupon_hdr' ,\n" +
                "    id\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "     ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM cafe_coupon_hdr\n" +
                "    WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'cafe_coupon_hdr', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='cos_egc_det' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM cos_egc_det  ;\n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'cos_egc_det' ,\n" +
                "    id\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || coupon_no\n" +
                "     ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM cos_egc_det\n" +
                " WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'cos_egc_det', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='cos_egc_hdr' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM cos_egc_hdr  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'cos_egc_hdr' ,\n" +
                "    id\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "     ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM cos_egc_hdr\n" +
                "  WHERE  last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt ;\n" +
                "  \n" +
                "    INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'cos_egc_hdr', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "      \n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='xrate_override_user' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM xrate_override_user  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "   INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'xrate_override_user' ,\n" +
                "    user_id\n" +
                "     ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM xrate_override_user\n" +
                "   WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'xrate_override_user', v_log_dt FROM dual;\n" +
                "   end if ;\n" +
                "\n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='xratemas' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM xratemas  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "     INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'xratemas' ,\n" +
                "    currency\n" +
                "     ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM xratemas\n" +
                "   WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'xratemas', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='park_pmt' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM park_pmt  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "       INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS \n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'park_pmt' ,\n" +
                "    seq_no\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "     || ','\n" +
                "    || DATE_\n" +
                "     || ','\n" +
                "    || time\n" +
                "     ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM park_pmt\n" +
                "   WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'park_pmt', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "\n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='vwp_dtl' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM vwp_dtl  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then  \n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS \n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'vwp_dtl' ,\n" +
                "    INSTIT_CDE\n" +
                "    || ','\n" +
                "    || VWP_CDE\n" +
                "     || ','\n" +
                "    || VOUCHER_NO \n" +
                "     ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM vwp_dtl\n" +
                "  WHERE  last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt ;\n" +
                "  \n" +
                "    INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'vwp_dtl', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                " \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='pos_user' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM pos_user  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "         INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS \n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'pos_user' ,\n" +
                "    user_id\n" +
                "     ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM pos_user\n" +
                "  WHERE  last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt ;\n" +
                "  \n" +
                "    INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'pos_user', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sastafftxn' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sastafftxn  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "        INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS \n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sastafftxn' ,\n" +
                "    tx_date || ',' || loc_code || ',' || reg_no|| ',' || tx_no || ',' || SEQ_NO\n" +
                "     ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sastafftxn\n" +
                "    WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sastafftxn', v_log_dt FROM dual;\n" +
                " end if;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sastafftxn_cl' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sastafftxn_cl  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS  \n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sastafftxn_cl' ,\n" +
                "    tx_date || ',' || loc_code || ',' || reg_no|| ',' || tx_no || ',' || SEQ_NO \n" +
                "     ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sastafftxn_cl\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sastafftxn_cl', v_log_dt FROM dual;  \n" +
                " end if;\n" +
                " \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='sagoa_txn' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM sagoa_txn  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS  \n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'sagoa_txn' ,\n" +
                "    tx_date || ',' || loc_code || ',' || reg_no|| ',' || tx_no || ',' || seq_no\n" +
                "     ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM sagoa_txn\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'sagoa_txn', v_log_dt FROM dual;  \n" +
                " end if;\n" +
                " \n" +
                "    COMMIT WORK; \n" +
                "END;";

        InsertMergeDataLog = "Declare\n" +
                "  v_log_dt         TIMESTAMP(3);\n" +
                "  v_LAST_SYNC_TIME TIMESTAMP(3);\n" +
                "  v_MAX_BATCH      VARCHAR2(100);\n" +
                "BEGIN\n" +
                "  \n" +
                "  SELECT NVL(max(BATCH_NO),0)\n" +
                "  INTO v_MAX_BATCH\n" +
                "  FROM DATA_UPDATE_LOG ;\n" +
                "  \n" +
                "  v_MAX_BATCH := v_MAX_BATCH + 1 ; \n" +
                "  v_LAST_SYNC_TIME := null;\n" +
                "  v_log_dt := null;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_SYNC where TABLE_NAME ='cafe_coupon_hdr' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM cafe_coupon_hdr  ;\n" +
                " \n" +
                "IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null and v_log_dt > v_LAST_SYNC_TIME then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG\n" +
                "    (\n" +
                "      ENTITY_NAME, \n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'cafe_coupon_hdr' ,\n" +
                "    entity_key ,\n" +
                "    v_log_dt ,\n" +
                "    systimestamp ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'A' ,\n" +
                "    NULL\n" +
                "  FROM cafe_coupon_hdr\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "\n" +
                "  INSERT INTO DATA_UPDATE_LOG_SYNC\n" +
                "    (Table_name , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'cafe_coupon_hdr', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "\n" +
                "   v_LAST_SYNC_TIME := null;\n" +
                "  v_log_dt := null;\n" +
                " \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_SYNC where TABLE_NAME ='cos_egc_hdr' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM cos_egc_hdr  ;\n" +
                " \n" +
                "IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null and v_log_dt > v_LAST_SYNC_TIME then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG\n" +
                "    (\n" +
                "      ENTITY_NAME, \n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'cos_egc_hdr' ,\n" +
                "    entity_key ,\n" +
                "    v_log_dt ,\n" +
                "    systimestamp ,\n" +
                "    v_MAX_BATCH ,\n" +
                "     'A' ,\n" +
                "    NULL\n" +
                "  FROM cos_egc_hdr\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "\n" +
                "  INSERT INTO DATA_UPDATE_LOG_SYNC\n" +
                "    (Table_name , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'cos_egc_hdr', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "   v_LAST_SYNC_TIME := null;\n" +
                "  v_log_dt := null;\n" +
                " \n" +
                "   SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_SYNC where TABLE_NAME ='xrate_override_user' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM xrate_override_user  ;\n" +
                " \n" +
                "IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null and v_log_dt > v_LAST_SYNC_TIME then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG\n" +
                "    (\n" +
                "      ENTITY_NAME, \n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'xrate_override_user' ,\n" +
                "    entity_key ,\n" +
                "    v_log_dt ,\n" +
                "    systimestamp ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    0 ,\n" +
                "    NULL\n" +
                "  FROM xrate_override_user\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "\n" +
                "  INSERT INTO DATA_UPDATE_LOG_SYNC\n" +
                "    (Table_name , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'xrate_override_user', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "   v_LAST_SYNC_TIME := null;\n" +
                "  v_log_dt := null;\n" +
                " \n" +
                "   SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_SYNC where TABLE_NAME ='xratemas' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM xratemas  ;\n" +
                " \n" +
                "IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null and v_log_dt > v_LAST_SYNC_TIME then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG\n" +
                "    (\n" +
                "      ENTITY_NAME, \n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'xratemas' ,\n" +
                "    entity_key ,\n" +
                "    v_log_dt ,\n" +
                "    systimestamp ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    0 ,\n" +
                "    NULL\n" +
                "  FROM xratemas\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "\n" +
                "  INSERT INTO DATA_UPDATE_LOG_SYNC\n" +
                "    (Table_name , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'xratemas', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "   v_LAST_SYNC_TIME := null;\n" +
                "  v_log_dt := null;\n" +
                " \n" +
                "   SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_SYNC where TABLE_NAME ='park_pmt' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM park_pmt  ;\n" +
                " \n" +
                "IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null and v_log_dt > v_LAST_SYNC_TIME then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG\n" +
                "    (\n" +
                "      ENTITY_NAME, \n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'park_pmt' ,\n" +
                "    entity_key ,\n" +
                "    v_log_dt ,\n" +
                "    systimestamp ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    0 ,\n" +
                "    NULL\n" +
                "  FROM park_pmt\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "\n" +
                "  INSERT INTO DATA_UPDATE_LOG_SYNC\n" +
                "    (Table_name , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'park_pmt', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "   v_LAST_SYNC_TIME := null;\n" +
                "  v_log_dt := null;\n" +
                " \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_SYNC where TABLE_NAME ='pos_user' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM pos_user  ;\n" +
                " \n" +
                "IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null and v_log_dt > v_LAST_SYNC_TIME then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG\n" +
                "    (\n" +
                "      ENTITY_NAME, \n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'pos_user' ,\n" +
                "    entity_key ,\n" +
                "    v_log_dt ,\n" +
                "    systimestamp ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    0 ,\n" +
                "    NULL\n" +
                "  FROM pos_user\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_SYNC\n" +
                "    (Table_name , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'pos_user', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "   v_LAST_SYNC_TIME := null;\n" +
                "  v_log_dt := null;\n" +
                " \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_SYNC where TABLE_NAME ='vwp_dtl' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM vwp_dtl  ;\n" +
                " \n" +
                "IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null and v_log_dt > v_LAST_SYNC_TIME then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG\n" +
                "    (\n" +
                "      ENTITY_NAME, \n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'vwp_dtl' ,\n" +
                "    entity_key ,\n" +
                "    v_log_dt ,\n" +
                "    systimestamp ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    0 ,\n" +
                "    NULL\n" +
                "  FROM vwp_dtl\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "\n" +
                "  INSERT INTO DATA_UPDATE_LOG_SYNC\n" +
                "    (Table_name , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'vwp_dtl', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "    v_LAST_SYNC_TIME := null;\n" +
                "  v_log_dt := null;\n" +
                " \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_SYNC where TABLE_NAME ='reason' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM reason  ;\n" +
                " \n" +
                "IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null and v_log_dt > v_LAST_SYNC_TIME then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG\n" +
                "    (\n" +
                "      ENTITY_NAME, \n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'reason' ,\n" +
                "    entity_key ,\n" +
                "    v_log_dt ,\n" +
                "    systimestamp ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    0 ,\n" +
                "    NULL\n" +
                "  FROM reason\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "\n" +
                "  INSERT INTO DATA_UPDATE_LOG_SYNC\n" +
                "    (Table_name , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'reason', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "\n" +
                "   COMMIT WORK;\n" +
                "END;";

        CouponPsTxCount = "Declare\n" +
                "    v_entity_key VARCHAR2(100);\n" +
                "    v_chkrtn NUMBER := 0;\n" +
                "    v_chk NUMBER := 0;\n" +
                "    v_cntCafecoupondet NUMBER := 0;\n" +
                "    v_cntCosegcdet NUMBER := 0;\n" +
                "    \n" +
                "Cursor c1\n" +
                "is\n" +
                "select entity_key \n" +
                "from data_update_log a\n" +
                "where a.entity_name ='cafe_coupon_hdr'  \n" +
                "and remark is null\n" +
                "and is_comp ='A';\n" +
                "\n" +
                "Cursor c2\n" +
                "is\n" +
                "select entity_key \n" +
                "from data_update_log a\n" +
                "where a.entity_name ='cos_egc_hdr'  \n" +
                "and remark is null\n" +
                "and is_comp ='A';\n" +
                "\n" +
                "begin\n" +
                "OPEN c1;\n" +
                "LOOP\n" +
                "    FETCH c1 INTO v_entity_key;    \n" +
                "    EXIT WHEN c1%NOTFOUND;  \n" +
                "\n" +
                "    v_chk := 0;\n" +
                "    v_chkrtn :=0;\n" +
                "    \n" +
                "   select count(*) into v_chkrtn from cafe_coupon_det a\n" +
                "   where a.entity_key = v_entity_key;\n" +
                "     \n" +
                "   if v_chkrtn = 0 then \n" +
                "      v_chk := 1;\n" +
                "      update data_update_log set remark ='Fail cafe_coupon_det'\n" +
                "      where entity_name ='cafe_coupon_hdr' \n" +
                "      and entity_key = v_entity_key ;\n" +
                "    end if;\n" +
                "    \n" +
                "if v_chk = 0 then\n" +
                "  select count(*) into v_cntCafecoupondet from cafe_coupon_det a \n" +
                "  where a.entity_key = v_entity_key;\n" +
                "    if v_cntCafecoupondet > 0 then \n" +
                "      INSERT\n" +
                "      INTO PS_TX_COUNT\n" +
                "        (\n" +
                "          ENTITY_NAME,\n" +
                "          ENTITY_KEY,\n" +
                "          ENTITY_UPD_DT,\n" +
                "          LOG_DT,\n" +
                "          IS_COMP,\n" +
                "          CHILD_NAME,\n" +
                "          CHILD_COUNT)\n" +
                "         VALUES\n" +
                "        ( 'cafe_coupon_hdr',\n" +
                "          v_entity_key,\n" +
                "          systimestamp,\n" +
                "          systimestamp,\n" +
                "          0,\n" +
                "          'cafe_coupon_det',\n" +
                "          v_cntCafecoupondet);\n" +
                "      end if; \n" +
                "        update data_update_log set remark ='logged' ,is_comp = 0\n" +
                "        where entity_name ='cafe_coupon_hdr' \n" +
                "        and entity_key = v_entity_key ;\n" +
                "  end if; \n" +
                "  \n" +
                "  END LOOP;\n" +
                "  CLOSE c1;\n" +
                "  COMMIT ;\n" +
                " \n" +
                "OPEN c2;\n" +
                "LOOP\n" +
                "    FETCH c2 INTO v_entity_key;    \n" +
                "    EXIT WHEN c2%NOTFOUND;  \n" +
                "\n" +
                "    v_chk := 0;\n" +
                "    v_chkrtn :=0;\n" +
                "    \n" +
                "   select count(*) into v_chkrtn from cos_egc_det a\n" +
                "   where a.entity_key = v_entity_key;\n" +
                "     \n" +
                "   if v_chkrtn = 0 then \n" +
                "      v_chk := 1;\n" +
                "      update data_update_log set remark ='Fail cos_egc_det'\n" +
                "      where entity_name ='cos_egc_hdr' \n" +
                "      and entity_key = v_entity_key ;\n" +
                "    end if;\n" +
                "    \n" +
                "if v_chk = 0 then\n" +
                "  select count(*) into v_cntCosegcdet from cos_egc_det a \n" +
                "  where a.entity_key = v_entity_key;\n" +
                "    if v_cntCosegcdet > 0 then \n" +
                "      INSERT\n" +
                "      INTO PS_TX_COUNT\n" +
                "        (\n" +
                "          ENTITY_NAME,\n" +
                "          ENTITY_KEY,\n" +
                "          ENTITY_UPD_DT,\n" +
                "          LOG_DT,\n" +
                "          IS_COMP,\n" +
                "          CHILD_NAME,\n" +
                "          CHILD_COUNT)\n" +
                "         VALUES\n" +
                "        ( 'cos_egc_hdr',\n" +
                "          v_entity_key,\n" +
                "          systimestamp,\n" +
                "          systimestamp,\n" +
                "          0,\n" +
                "          'cos_egc_det',\n" +
                "          v_cntCosegcdet);\n" +
                "      end if; \n" +
                "        update data_update_log set remark ='logged' ,is_comp = 0\n" +
                "        where entity_name ='cos_egc_hdr' \n" +
                "        and entity_key = v_entity_key ;\n" +
                "  end if; \n" +
                "  \n" +
                "  END LOOP;\n" +
                "  CLOSE c2;\n" +
                "  COMMIT ;\n" +
                "\n" +
                "end;";

        StockResUpdDate = "Declare\n" +
                "  v_log_dt         TIMESTAMP(3);\n" +
                "  v_LAST_SYNC_TIME TIMESTAMP(3);\n" +
                "  v_MAX_BATCH      VARCHAR2(100);\n" +
                "  v_remark         varchar2(100);\n" +
                "BEGIN\n" +
                "  v_log_dt := SYSTIMESTAMP;\n" +
                "  \n" +
                "    SELECT NVL(max(BATCH_NO),0) \n" +
                "  INTO v_MAX_BATCH\n" +
                "  FROM data_update_log_pos;\n" +
                "  \n" +
                "  v_MAX_BATCH := v_MAX_BATCH + 1 ;\n" +
                "    v_remark := 'Stock' ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='stock_res' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM stock_res  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'stock_res' ,\n" +
                "    LOC_CODE\n" +
                "    || ','\n" +
                "    || RES_NO ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM stock_res\n" +
                "   WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'stock_res', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                " SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='stockres_det' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM stockres_det  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'stockres_det' ,\n" +
                "    LOC_CODE\n" +
                "    || ','\n" +
                "    || RES_NO\n" +
                "    || ','\n" +
                "    || RES_SEQ,\n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM stockres_det\n" +
                "   WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'stockres_det', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "    COMMIT WORK;\n" +
                "END;";

        InsertStockResDataLog = "Declare\n" +
                "  v_log_dt         TIMESTAMP(3);\n" +
                "  v_LAST_SYNC_TIME TIMESTAMP(3);\n" +
                "  v_MAX_BATCH      VARCHAR2(100);\n" +
                "BEGIN\n" +
                "  \n" +
                "  SELECT NVL(max(BATCH_NO),0) \n" +
                "  INTO v_MAX_BATCH\n" +
                "  FROM DATA_UPDATE_LOG ;\n" +
                "  \n" +
                "  v_MAX_BATCH := v_MAX_BATCH + 1 ;\n" +
                "  \n" +
                "  --itemmas \n" +
                "  v_LAST_SYNC_TIME := null;\n" +
                "  v_log_dt := null;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_SYNC where TABLE_NAME ='stock_res' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM stock_res  ;\n" +
                " \n" +
                "IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null and v_log_dt > v_LAST_SYNC_TIME then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'stock_res' ,\n" +
                "    entity_key ,\n" +
                "    v_log_dt ,\n" +
                "    systimestamp ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'A' ,\n" +
                "    NULL\n" +
                "  FROM stock_res\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "\n" +
                "  INSERT INTO DATA_UPDATE_LOG_SYNC\n" +
                "    (Table_name , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'stock_res', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "   COMMIT WORK;\n" +
                "END;";

        StockResPsTxCount = "Declare\n" +
                "v_stockres_entity_key VARCHAR2(100);\n" +
                "v_cntstockres_det NUMBER := 0;\n" +
                "v_stockreschk NUMBER := 0;\n" +
                "\n" +
                "Cursor c2\n" +
                "is\n" +
                "select entity_key \n" +
                "from data_update_log a\n" +
                "where a.entity_name ='stock_res'  \n" +
                "and is_comp ='A';\n" +
                "\n" +
                "begin\n" +
                "OPEN c2;\n" +
                "LOOP\n" +
                "    FETCH c2 INTO v_stockres_entity_key;    \n" +
                "    EXIT WHEN c2%NOTFOUND;  \n" +
                "    \n" +
                "    v_stockreschk := 0; \n" +
                "  \n" +
                "    select count(*) into v_cntstockres_det from STOCKRES_DET a \n" +
                "    where a.entity_key = v_stockres_entity_key;\n" +
                "    \n" +
                "    if v_cntstockres_det > 0 then \n" +
                "    INSERT\n" +
                "    INTO PS_TX_COUNT\n" +
                "      (\n" +
                "        ENTITY_NAME,\n" +
                "        ENTITY_KEY,\n" +
                "        ENTITY_UPD_DT,\n" +
                "        LOG_DT,\n" +
                "        IS_COMP,\n" +
                "        CHILD_NAME,\n" +
                "        CHILD_COUNT)\n" +
                "       VALUES\n" +
                "      ( 'stock_res',\n" +
                "        v_stockres_entity_key,\n" +
                "        systimestamp,\n" +
                "        systimestamp,\n" +
                "        0,\n" +
                "        'stockres_det',\n" +
                "        v_cntstockres_det);\n" +
                "    end if; \n" +
                "    \n" +
                "     update data_update_log set remark ='logged',is_comp = 0\n" +
                "     where entity_name ='stock_res' \n" +
                "     and entity_key = v_stockres_entity_key ;\n" +
                "      \n" +
                "     update stock_res set is_log = 1 \n" +
                "     where entity_key = v_stockres_entity_key ;\n" +
                "            \n" +
                "  END LOOP;\n" +
                "  CLOSE c2;\n" +
                "  COMMIT ;\n" +
                "end;";

        DepositUpdate = "Declare\n" +
                "  v_log_dt         TIMESTAMP(3);\n" +
                "  v_LAST_SYNC_TIME TIMESTAMP(3);\n" +
                "  v_MAX_BATCH      VARCHAR2(100);\n" +
                "  v_remark         varchar2(100);\n" +
                "BEGIN\n" +
                "  v_log_dt := SYSTIMESTAMP;\n" +
                "  \n" +
                "  SELECT NVL(max(BATCH_NO),0) \n" +
                "  INTO v_MAX_BATCH\n" +
                "  FROM data_update_log_pos ;\n" +
                "  \n" +
                " -- SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC WHERE remark = 'Deposit'; \n" +
                "  v_MAX_BATCH := v_MAX_BATCH + 1 ;\n" +
                "  v_remark := 'Deposit' ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='dphdr' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM dphdr  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'dphdr' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || Dp_No  || ','\n" +
                "    || Issue_Loc_Code , \n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM dphdr\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'dphdr', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='dpdet' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM dpdet  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'dpdet' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || Dp_No  || ','\n" +
                "    || Issue_Loc_Code , \n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM dpdet\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt \n" +
                "   and last_upd_dt >  v_LAST_SYNC_TIME;\n" +
                "   \n" +
                "  INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'dpdet', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='dppayment' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM dppayment  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'dppayment' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || Dp_No  || ','\n" +
                "    || payment_no , \n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM dppayment\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt ;\n" +
                "  \n" +
                "   INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'dppayment', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='presell_dp_vip' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM presell_dp_vip  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'presell_dp_vip' ,\n" +
                "    loc_code\n" +
                "    || ','\n" +
                "    || dp_no , \n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM presell_dp_vip\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt ;\n" +
                "  \n" +
                "   INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'presell_dp_vip', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='presell_item' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM presell_item  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'presell_item' ,\n" +
                "    sku\n" +
                "    || ','\n" +
                "    || event_id\n" +
                "    || ','\n" +
                "    || po_no , \n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM presell_item\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt ;\n" +
                "  \n" +
                "   INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'presell_item', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='presell_item_status' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM presell_item_status  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'presell_item_status' ,\n" +
                "    tx_date\n" +
                "    || ','\n" +
                "    || loc_code\n" +
                "    || ','\n" +
                "    || reg_no\n" +
                "    || ','\n" +
                "    || tx_no\n" +
                "    || ','\n" +
                "    || seq_no \n" +
                "    , \n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM presell_item_status\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt ;\n" +
                "  \n" +
                "   INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'presell_item_status', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                " SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='br_gift' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM BR_GIFT  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'br_gift' ,\n" +
                "    BRIDE_KEY\n" +
                "    || ','\n" +
                "    || Gift_key\n" +
                "    || ','\n" +
                "    || REQUEST_LOC\n" +
                "    , \n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM BR_GIFT\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt ;\n" +
                "  \n" +
                "   INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'br_gift', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                " SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='br_guest' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM br_guest  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'br_guest' ,\n" +
                "    Bridal_key\n" +
                "    , \n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM br_guest\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt ;\n" +
                "  \n" +
                "   INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'br_guest', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='br_reg' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM br_reg  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'br_reg' ,\n" +
                "    BRIDE_KEY\n" +
                "    , \n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM br_reg\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt ;\n" +
                "  \n" +
                "   INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'br_reg', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                "  SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='brdp_det' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM brdp_det  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'brdp_det' ,\n" +
                "     brdp_no\n" +
                "    || ','\n" +
                "    || bridal_reg_no\n" +
                "    || ','\n" +
                "    || issue_loc_code\n" +
                "    , \n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM brdp_det\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt ;\n" +
                "  \n" +
                "   INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'brdp_det', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                " \n" +
                " SELECT MAX(last_sync_time) INTO v_LAST_SYNC_TIME FROM DATA_UPDATE_LOG_POS_SYNC where remark ='brdp_hdr' ;\n" +
                "  SELECT MAX(LAST_UPD_DT) INTO v_log_dt FROM brdp_hdr  ;\n" +
                "  \n" +
                "  IF v_LAST_SYNC_TIME <> v_log_dt and v_LAST_SYNC_TIME is not null then\n" +
                "  INSERT\n" +
                "  INTO DATA_UPDATE_LOG_POS\n" +
                "    (\n" +
                "      ENTITY_NAME,\n" +
                "      ENTITY_KEY,\n" +
                "      ENTITY_UPD_DT,\n" +
                "      LOG_DT,\n" +
                "      BATCH_NO,\n" +
                "      IS_COMP,\n" +
                "      REMARK\n" +
                "    )\n" +
                "  SELECT 'brdp_hdr' ,\n" +
                "     brdp_no\n" +
                "    || ','\n" +
                "    || bridal_reg_no\n" +
                "    || ','\n" +
                "    || issue_loc_code\n" +
                "    , \n" +
                "    v_log_dt ,\n" +
                "    NULL ,\n" +
                "    v_MAX_BATCH ,\n" +
                "    'P' ,\n" +
                "    v_remark\n" +
                "  FROM brdp_hdr\n" +
                "  WHERE last_upd_dt BETWEEN v_LAST_SYNC_TIME AND v_log_dt ;\n" +
                "  \n" +
                "   INSERT INTO DATA_UPDATE_LOG_POS_SYNC\n" +
                "    (remark , LAST_SYNC_TIME\n" +
                "    )\n" +
                "  SELECT 'brdp_hdr', v_log_dt FROM dual;\n" +
                " end if ;\n" +
                "  \n" +
                "      COMMIT WORK;\n" +
                "END;";
    }
}
