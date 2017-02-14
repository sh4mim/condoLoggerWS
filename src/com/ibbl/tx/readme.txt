

        For making transaction you have to do the following steps:

            1. Get TxController instance
            2. Initialize persistence
            3. Commit transaction
            4. Catch Error and rollback transaction
            5. Add finally clause to close persistence.


        //......These are the sample code :

        TxController txc = TxController.getInstance();

        int txID = txc.initPersistence();

        try
        {
            Student student = new Student();
            student.setId(3);
            student.setName("Parvej");
            student.setFatherName("N Islam");

            StudentDAO dao = new StudentDAO(txID);
            dao.save(student);

            txc.commitPersistence(txID);
        }
        catch (TxException e)
        {
            txc.rollbackPersistence(txID);
        }
        finally
        {
            txc.closeTxSession(txID);
        }