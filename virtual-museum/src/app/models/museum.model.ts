import { Exhibition } from "./exhibition.model";

export interface Museum {
    
  id: number ;
  name: string | null;
  address: string | null;
  phone: string | null;
  city: string | null;
  country: string | null ;
  type: string | null;
  image: string | null;
  active: boolean | null;
  latitude: number | null;
  longitude: number | null;
  description: string | null;
  presentation: string | null;
  // exhibitions: Exhibition[] | null;

//   constructor(
//     id?: number,
//     name?: string,
//     address?: string,
//     phone?: string,
//     city?: string,
//     country?: string,
//     type?: string,
//     image?: string,
//     active?: boolean,
//     latitude?: number,
//     longitude?: number,
//     exhibitions?: Exhibition[]
//   ) {
//     this.id = id || null;
//     this.name = name || null;
//     this.address = address || null;
//     this.phone = phone || null;
//     this.city = city || null;
//     this.country = country || null;
//     this.type = type || null;
//     this.image = image || null;
//     this.active = active || null;
//     this.latitude = latitude || null;
//     this.longitude = longitude || null;
//     this.exhibitions = exhibitions || null;
//   }
}
