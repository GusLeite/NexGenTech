package br.com.gusleite.NexGenTech.enums;

public enum Office {
    TRAINEE {
        @Override
        public Office NextOffice() {
            return DEV_JUNIOR;
        }
    },
    DEV_JUNIOR {
        @Override
        public Office NextOffice() {
            return DEV_PLENO;
        }
    },
    DEV_PLENO {
        @Override
        public Office NextOffice() {
            return DEV_SENIOR;
        }
    },
    DEV_SENIOR {
        @Override
        public Office NextOffice() {
            return SCRUM_MASTER;
        }
    },
    SYSTEM_ANALYST {
        @Override
        public Office NextOffice() {
            return SCRUM_MASTER;
        }
    },
    DATA_SCIENTIST {
        @Override
        public Office NextOffice() {
            return SCRUM_MASTER;
        }
    },
    PRODUCT_OWNER {
        @Override
        public Office NextOffice() {
            return MANAGER;
        }
    },
    SCRUM_MASTER {
        @Override
        public Office NextOffice() {
            return PRODUCT_OWNER;
        }
    },
    MANAGER {
        @Override
        public Office NextOffice() {
            return MANAGER;
        }
    };

    public abstract Office NextOffice();

}
