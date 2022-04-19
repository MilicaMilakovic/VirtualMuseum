import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NbActionsModule, NbButtonModule, NbCardModule, NbFormFieldModule, NbContextMenuModule, NbSearchModule, 
         NbSidebarModule,NbLayoutModule,NbInputModule,NbIconModule,NbSelectModule   } from '@nebular/theme';
import { NbEvaIconsModule } from '@nebular/eva-icons';
import { FormsModule } from '@angular/forms';


const nebularModules = [
    NbSearchModule,
    NbCardModule,
    NbSidebarModule,
    NbButtonModule,
    NbActionsModule,
    NbContextMenuModule,
    NbLayoutModule ,
    NbInputModule,
    NbFormFieldModule,
    NbEvaIconsModule ,
    NbIconModule,
    NbSelectModule ,

];

@NgModule({
  declarations: [],
  imports: [
    ...nebularModules, FormsModule,
  ],
  exports: [
    ...nebularModules
  ]
})
export class AppNebularModule { }
