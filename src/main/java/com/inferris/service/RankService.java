package com.inferris.service;

import com.inferris.model.rank.DonorRank;
import com.inferris.model.rank.StaffRank;

public class RankService {
    public static StaffRank getStaffRank(int staffValue) {
        return StaffRank.getById(staffValue);
    }

    public static DonorRank getDonorRank(int donorValue) {
        return DonorRank.getById(donorValue);
    }
}
