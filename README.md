### Rest API map

Simple rest api (Spring boot)
* map
* points group
* points

# 

#### Map - all
##### Get all maps
| GET | /maps |
| --- | --- |

##### Get map
| GET | /maps/{uuid} |
| --- | --- |

#### Map - admin
##### Add map
| POST | /admin/maps |
| --- | --- |

`{"name": "UpdatedMap"}`

##### Update map
| PUT | /admin/maps/{uuid} |
| --- | --- |

`{"uuid": "...", "name": "NewMap"}`

##### Delete map
| DELETE | /admin/maps/{uuid} |
| --- | --- |

##### Add points group to map
| POST | /admin/maps/{mapUuid}/groups/{groupUuid} |
| --- | --- |

##### Delete points group from map
| DELETE | /admin/maps/{mapUuid}/groups/{groupUuid} |
| --- | --- |

---

#### Group points - all
##### Get all goups by map uuid
| GET | /maps/{mapUuid}/groups |
| --- | --- |

##### Get group (with points)
| GET | /groups/{uuid}/points |
| --- | --- |

#### Group points - admin

##### Create group in map
| POST | /admin/maps/{mapUuid}/groups |
| --- | --- |

`{"name": "New group"}`

##### Update group
| PUT | /admin/groups/{uuid} |
| --- | --- |

`{"name": "Update group"}`

##### Delete group
| DELETE | /admin/groups/{uuid} |
| --- | --- |

---

#### Points - all

#### Points - admin

##### Add points to group
| POST | /admin/groups/{groupUuid}/points
| --- | --- |

`[{"text": "Caption", "x": "1", "y": "1"}]`

##### Update point by uuid
| PUT | /admin/points/{pointUuid} |
| --- | --- |

`{"text": "Caption", "x": "1", "y": "1"}`

##### Delete point by uuid
| DELETE | /admin/points/{pointUuid} |
| --- | --- |
