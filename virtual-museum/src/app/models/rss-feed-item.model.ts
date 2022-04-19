export class RssFeedItem {
    title: string = "";
    content: string = "";
    link: string="";
    imgUrl: string = " ";

    constructor(title: string, content:string, link:string, imgUrl: string){
        this.title = title;
        this.content = content;
        this.link = link;
        this.imgUrl = imgUrl;
    }

}