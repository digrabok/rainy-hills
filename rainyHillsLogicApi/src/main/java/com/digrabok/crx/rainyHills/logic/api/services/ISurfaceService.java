package com.digrabok.crx.rainyHills.logic.api.services;

import com.digrabok.crx.rainyHills.logic.api.messages.requests.FetchSurfaceByIdRequest;
import com.digrabok.crx.rainyHills.logic.api.messages.requests.FetchSurfaceListRequest;
import com.digrabok.crx.rainyHills.logic.api.messages.requests.SurfaceCreateRequest;
import com.digrabok.crx.rainyHills.logic.api.messages.requests.SurfaceDeleteRequest;
import com.digrabok.crx.rainyHills.logic.api.messages.responses.FetchRequestByIdResponse;
import com.digrabok.crx.rainyHills.logic.api.messages.responses.FetchSurfaceListResponse;
import com.digrabok.crx.rainyHills.logic.api.messages.responses.SurfaceCreateResponse;
import com.digrabok.crx.rainyHills.logic.api.messages.responses.SurfaceDeleteResponse;

public interface ISurfaceService {
    SurfaceCreateResponse create(SurfaceCreateRequest request);
    SurfaceDeleteResponse delete(SurfaceDeleteRequest request);
    FetchRequestByIdResponse fetchSurface(FetchSurfaceByIdRequest request);
    FetchSurfaceListResponse fetchSurfaces(FetchSurfaceListRequest request);

}
