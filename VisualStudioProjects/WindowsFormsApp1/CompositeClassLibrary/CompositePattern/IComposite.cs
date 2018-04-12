namespace CompositeClassLibrary.CompositePattern
{
    internal interface IComposite
    {
        long YCoord { get; set; }

        //void CalculatePosition(int width, int resolutionInPixels);

        void Clear();
    }
}