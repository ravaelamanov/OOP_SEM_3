package BLLServicies;

import util.AbstractRepositoryFactory;

public abstract class BLLService {
    protected static AbstractRepositoryFactory factory;

    public BLLService(AbstractRepositoryFactory factory) {
        setFactory(factory);
    }

    public void setFactory(AbstractRepositoryFactory factory) {
        BLLService.factory = factory;
        initFactories(factory);
    }

    abstract protected void initFactories(AbstractRepositoryFactory factory);
}
